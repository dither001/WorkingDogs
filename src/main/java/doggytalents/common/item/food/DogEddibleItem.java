/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.item.food;

import java.util.List;
import java.util.function.Function;

import com.mojang.datafixers.util.Pair;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.common.item.IDogEddible;
import doggytalents.common.network.packet.ParticlePackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

// line 2 "../../../../food_dogfood.ump"
public abstract class DogEddibleItem extends Item implements IDogEddible {
	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	private static final FoodProperties EmptyProperties = (new FoodProperties.Builder()).nutrition(0).build();

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// DogEddibleItem Attributes
	private FoodProperties baseFoodProperties;
	private FoodProperties currentFoodProperties;
	private FoodProperties emptyFoodProperties;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DogEddibleItem(Properties aProperties) {
		super(aProperties);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setBaseFoodProperties(FoodProperties aBaseFoodProperties) {
		boolean wasSet = false;
		baseFoodProperties = aBaseFoodProperties;
		wasSet = true;
		return wasSet;
	}

	public boolean setCurrentFoodProperties(FoodProperties aCurrentFoodProperties) {
		boolean wasSet = false;
		currentFoodProperties = aCurrentFoodProperties;
		wasSet = true;
		return wasSet;
	}

	public boolean setEmptyFoodProperties(FoodProperties aEmptyFoodProperties) {
		boolean wasSet = false;
		emptyFoodProperties = aEmptyFoodProperties;
		wasSet = true;
		return wasSet;
	}

	public FoodProperties getBaseFoodProperties() {
		return baseFoodProperties;
	}

	public FoodProperties getCurrentFoodProperties() {
		return currentFoodProperties;
	}

	public FoodProperties getEmptyFoodProperties() {
		return emptyFoodProperties;
	}

	public void delete() {
	}

	// line 16 "../../../../food_dogfood.ump"
	public DogEddibleItem(Properties itemProps, FoodProperties foodProperties) {
		/* custom constructor */
		super(itemProps.food(EmptyProperties));
		this.baseFoodProperties = (null != foodProperties) ? foodProperties : EmptyProperties;

		Builder nullPropsBuilder = (new FoodProperties.Builder()).nutrition(0);
		boolean changed = false;
		if (foodProperties.canAlwaysEat()) {
			changed = true;
			nullPropsBuilder.alwaysEat();
		}

		emptyFoodProperties = changed ? nullPropsBuilder.build() : EmptyProperties;
		currentFoodProperties = emptyFoodProperties;
	}

	// line 32 "../../../../food_dogfood.ump"
	public DogEddibleItem(FoodProperties foodProperties) {
		/* custom constructor */
		this(new Properties(), foodProperties);
	}

	// line 42 "../../../../food_dogfood.ump"
	public DogEddibleItem(Function<Properties, Properties> itemBuilder, Function<Builder, Builder> foodBuilder) {
		/* custom constructor */
		this(itemBuilder.apply(new Properties()), foodBuilder.apply(new Builder()).build());
	}

	@Override
	// line 54 "../../../../food_dogfood.ump"
	public boolean canConsume(AbstractDog dog, ItemStack stackIn, Entity entityIn) {
		return !dog.isDefeated() && isFood(stackIn);
	}

	@Override
	// line 59 "../../../../food_dogfood.ump"
	public InteractionResult consume(AbstractDog dog, ItemStack stack, Entity entityIn) {
		if (dog.level().isClientSide)
			return InteractionResult.SUCCESS;

		var dogEddible = this;

		if (!dogEddible.alwaysEatWhenDogConsume(dog) && !dog.canStillEat()) {
			return InteractionResult.FAIL;
		}

		if (!dog.level().isClientSide) {
			float heal = dogEddible.getAddedHungerWhenDogConsume(stack, dog);

			dog.addHunger(heal);
			dog.consumeItemFromStack(entityIn, stack);

			for (var pair : dogEddible.getAdditionalEffectsWhenDogConsume(stack, dog)) {
				if (pair.getFirst() != null && dog.getRandom().nextFloat() < pair.getSecond()) {
					dog.addEffect(new MobEffectInstance(pair.getFirst()));
				}
			}

			if (dog.level() instanceof ServerLevel) {
				ParticlePackets.DogEatingParticlePacket.sendDogEatingParticlePacketToNearby(dog, new ItemStack(this));
			}
			dog.playSound(dogEddible.getDogEatingSound(dog), dog.getSoundVolume(),
					(dog.getRandom().nextFloat() - dog.getRandom().nextFloat()) * 0.2F + 1.0F);

			var returnStack = dogEddible.getReturnStackAfterDogConsume(stack, dog);
			if (!returnStack.isEmpty()) {
				dog.spawnAtLocation(returnStack);
			}
		}

		return InteractionResult.SUCCESS;
	}

	@Override
	// line 97 "../../../../food_dogfood.ump"
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (entity instanceof Player)
			currentFoodProperties = baseFoodProperties;
		var ret = super.finishUsingItem(stack, level, entity);
		currentFoodProperties = emptyFoodProperties;
		return ret;
	}

	@Override
	// line 106 "../../../../food_dogfood.ump"
	public float getAddedHungerWhenDogConsume(ItemStack useStack, AbstractDog dog) {
		return baseFoodProperties.getNutrition() * 5;
	}

	@Override
	// line 117 "../../../../food_dogfood.ump"
	public FoodProperties getFoodProperties() {
		return this.currentFoodProperties;
	}

	@Override
	// line 122 "../../../../food_dogfood.ump"
	public boolean isFood(ItemStack stack) {
		return stack.getItem() == this;
	}

	public String toString() {
		return super.toString() + "[" + "]" + System.getProperties().getProperty("line.separator") + "  "
				+ "baseFoodProperties" + "="
				+ (getBaseFoodProperties() != null ? !getBaseFoodProperties().equals(this)
						? getBaseFoodProperties().toString().replaceAll("  ", "    ")
						: "this" : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "currentFoodProperties" + "="
				+ (getCurrentFoodProperties() != null ? !getCurrentFoodProperties().equals(this)
						? getCurrentFoodProperties().toString().replaceAll("  ", "    ")
						: "this" : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "emptyFoodProperties" + "="
				+ (getEmptyFoodProperties() != null ? !getEmptyFoodProperties().equals(this)
						? getEmptyFoodProperties().toString().replaceAll("  ", "    ")
						: "this" : "null");
	}
	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 36 "../../../../food_dogfood.ump"
	public DogEddibleItem(Function<Builder, FoodProperties.Builder> propsCreator) {
		/* custom constructor */
		this(new Properties(), propsCreator.apply(new FoodProperties.Builder()).build());
	}

// line 110 "../../../../food_dogfood.ump"
	public List<Pair<MobEffectInstance, Float>> getAdditionalEffectsWhenDogConsume(ItemStack useStack,
			AbstractDog dog) {
		return baseFoodProperties.getEffects();
	}

}
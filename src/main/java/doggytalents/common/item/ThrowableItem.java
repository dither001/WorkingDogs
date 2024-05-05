/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.item;

import java.util.function.Supplier;

import doggytalents.api.feature.EnumMode;
import doggytalents.api.inferface.IThrowableItem;
import doggytalents.common.entity.Dog;
import doggytalents.common.entity.ai.triggerable.DogFetchAction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

// line 2 "../../../item_throwable.ump"
public class ThrowableItem extends Item implements IThrowableItem {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public ThrowableItem(Properties aProperties) {
		super(aProperties);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	@Override
	// line 24 "../../../item_throwable.ump"
	public ItemStack getCustomRenderStack(ItemStack stack) {
		return new ItemStack(this.renderBone.get());
	}

	@Override
	// line 29 "../../../item_throwable.ump"
	public ItemStack getReturnStack(ItemStack stack) {
		ItemStack returnStack = new ItemStack(this.altBone.get());
		if (stack.hasTag()) {
			returnStack.setTag(stack.getTag().copy());
		}

		return returnStack;
	}

	@Override
	// line 39 "../../../item_throwable.ump"
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemStackIn = playerIn.getItemInHand(handIn);

		worldIn.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ARROW_SHOOT,
				SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.random.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isClientSide) {
			ItemStack stack = itemStackIn.copy();
			stack.setCount(1);
			ItemEntity entityitem = new ItemEntity(playerIn.level(), playerIn.getX(),
					(playerIn.getY() - 0.30000001192092896D) + playerIn.getEyeHeight(), playerIn.getZ(), stack);
			entityitem.setPickUpDelay(20);
			this.setHeadingFromThrower(entityitem, playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.2F, 1.0F);
			worldIn.addFreshEntity(entityitem);
			triggerNearbyDogs(playerIn, entityitem);
		}

		if (!playerIn.getAbilities().instabuild)
			itemStackIn.shrink(1);

		playerIn.awardStat(Stats.ITEM_USED.get(this));
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, itemStackIn);
	}

	/**
	 * 
	 */
	// line 66 "../../../item_throwable.ump"
	private void setHeadingFromThrower(ItemEntity entityItem, Entity entityThrower, float rotationPitchIn,
			float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
		float f = -Mth.sin(rotationYawIn * ((float) Math.PI / 180F))
				* Mth.cos(rotationPitchIn * ((float) Math.PI / 180F));
		float f1 = -Mth.sin((rotationPitchIn + pitchOffset) * ((float) Math.PI / 180F));
		float f2 = Mth.cos(rotationYawIn * ((float) Math.PI / 180F))
				* Mth.cos(rotationPitchIn * ((float) Math.PI / 180F));
		this.setThrowableHeading(entityItem, f, f1, f2, velocity, inaccuracy);
		Vec3 vec3d = entityThrower.getDeltaMovement();
		entityItem.setDeltaMovement(
				entityItem.getDeltaMovement().add(vec3d.x, entityThrower.onGround() ? 0.0D : vec3d.y, vec3d.z));
	}

	// line 79 "../../../item_throwable.ump"
	private void setThrowableHeading(ItemEntity entityItem, double x, double y, double z, float velocity,
			float inaccuracy) {
		Vec3 vec3d = (new Vec3(x, y, z)).normalize()
				.add(entityItem.level().random.nextGaussian() * 0.0075F * inaccuracy,
						entityItem.level().random.nextGaussian() * 0.0075F * inaccuracy,
						entityItem.level().random.nextGaussian() * 0.0075F * inaccuracy)
				.scale(velocity);
		entityItem.setDeltaMovement(vec3d);
		float f = Mth.sqrt((float) (vec3d.x * vec3d.x + vec3d.z * vec3d.z));
		entityItem.setYRot((float) (Mth.atan2(vec3d.x, vec3d.z) * (180F / (float) Math.PI)));
		entityItem.setXRot((float) (Mth.atan2(vec3d.y, f) * (180F / (float) Math.PI)));
		entityItem.yRotO = entityItem.getYRot();
		entityItem.xRotO = entityItem.getXRot();
	}

	// line 94 "../../../item_throwable.ump"
	private void triggerNearbyDogs(Player player, ItemEntity entityitem) {
		final int RADIUS = 5;
		var dogs = player.level().getEntitiesOfClass(Dog.class, player.getBoundingBox().inflate(RADIUS, 3, RADIUS),
				d -> d.canInteract(player) && !d.isBusy() && !d.hasBone() && !d.isOrderedToSit()
						&& d.getMode() == EnumMode.DOCILE);
		for (var dog : dogs) {
			dog.triggerAction(new DogFetchAction(dog, player, entityitem));
		}
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 12 "../../../item_throwable.ump"
	public ThrowableItem(Supplier<? extends Item> altBone, Supplier<? extends Item> renderBone, Properties properties) {
		/* custom constructor */
		this(properties);
		this.altBone = altBone;
		this.renderBone = renderBone;
	}

// line 19 "../../../item_throwable.ump"
	public Supplier<? extends Item> altBone;
// line 20 "../../../item_throwable.ump"
	public Supplier<? extends Item> renderBone;

}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.item.food;

import java.util.function.Function;

import doggytalents.api.inferface.AbstractDog;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

// line 2 "../../../../food_bowldogfood.ump"
public abstract class DogEddibleBowlFoodItem extends DogEddibleItem {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DogEddibleBowlFoodItem(Properties aProperties) {
		super(aProperties);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
		super.delete();
	}

	// line 13 "../../../../food_bowldogfood.ump"
	public DogEddibleBowlFoodItem(Properties itemProps, FoodProperties foodProps) {
		/* custom constructor */
		super(itemProps.craftRemainder(Items.BOWL), foodProps);
	}

	// line 18 "../../../../food_bowldogfood.ump"
	public DogEddibleBowlFoodItem(FoodProperties foodProperties) {
		/* custom constructor */
		this(new Properties(), foodProperties);
	}

	// line 23 "../../../../food_bowldogfood.ump"
	public DogEddibleBowlFoodItem(Function<Builder, Builder> propsCreator) {
		/* custom constructor */
		this(new Properties(), propsCreator.apply(new Builder()).build());
	}

	@Override
	// line 35 "../../../../food_bowldogfood.ump"
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
		ItemStack returnStack = super.finishUsingItem(stack, level, user);
		boolean creativeUse = user instanceof Player && ((Player) user).getAbilities().instabuild;
		if (creativeUse)
			return returnStack;

		if (returnStack.isEmpty())
			return new ItemStack(Items.BOWL);

		if (!(user instanceof Player player))
			return returnStack;

		if (player.level().isClientSide)
			return returnStack;

		var bonusReturnStack = new ItemStack(Items.BOWL);
		var inv = player.getInventory();
		int freeSlot = inv.getFreeSlot();
		if (freeSlot >= 0)
			inv.add(bonusReturnStack);
		else
			player.spawnAtLocation(bonusReturnStack);

		return returnStack;
	}

	@Override
	// line 62 "../../../../food_bowldogfood.ump"
	public ItemStack getReturnStackAfterDogConsume(ItemStack useStack, AbstractDog dog) {
		return new ItemStack(Items.BOWL);
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 27 "../../../../food_bowldogfood.ump"
	public DogEddibleBowlFoodItem(Function<Item.Properties, Item.Properties> itemPropsCreator,
			Function<Builder, Builder> propsCreator) {
		/* custom constructor */
		this(itemPropsCreator.apply(new Properties()), propsCreator.apply(new Builder()).build());
	}

}
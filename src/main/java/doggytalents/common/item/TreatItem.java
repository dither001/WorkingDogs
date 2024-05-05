/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.item;

import doggytalents.api.feature.DogLevel;
import doggytalents.api.feature.DogLevel.Type;
import doggytalents.api.inferface.AbstractDog;
import doggytalents.api.inferface.IDogItem;
import doggytalents.common.lib.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

// line 2 "../../../item_dogtreat.ump"
public class TreatItem extends Item implements IDogItem {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// TreatItem Attributes
	private int maxLevel;
	private Type type;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public TreatItem(Properties aProperties, int aMaxLevel, Type aType) {
		super(aProperties);
		maxLevel = aMaxLevel;
		type = aType;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setMaxLevel(int aMaxLevel) {
		boolean wasSet = false;
		maxLevel = aMaxLevel;
		wasSet = true;
		return wasSet;
	}

	public boolean setType(Type aType) {
		boolean wasSet = false;
		type = aType;
		wasSet = true;
		return wasSet;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public Type getType() {
		return type;
	}

	public void delete() {
	}

	@Override
	// line 16 "../../../item_dogtreat.ump"
	public InteractionResult processInteract(AbstractDog dog, Level world, Player player, InteractionHand hand) {
		if (!dog.isTame() || !dog.canInteract(player)) {
			return InteractionResult.FAIL;
		}

		DogLevel dogLevel = dog.getDogLevel();
		if (dog.getAge() < 0) {
			if (!world.isClientSide) {
				world.broadcastEntityEvent(dog, Constants.EntityState.WOLF_SMOKE);
				player.sendSystemMessage(Component.translatable("treat." + this.type.getName() + ".too_young"));
			}

			return InteractionResult.CONSUME;
		} else if (!dogLevel.canIncrease(this.type)) {
			if (!world.isClientSide) {
				world.broadcastEntityEvent(dog, Constants.EntityState.WOLF_SMOKE);
				player.sendSystemMessage(Component.translatable("treat." + this.type.getName() + ".low_level"));
			}

			return InteractionResult.CONSUME;
		} else if (dogLevel.getLevel(this.type) < this.maxLevel) {
			if (!player.level().isClientSide) {
				if (!player.getAbilities().instabuild) {
					player.getItemInHand(hand).shrink(1);
				}

				dog.increaseLevel(this.type);
				dog.setOrderedToSit(true);
				world.broadcastEntityEvent(dog, Constants.EntityState.WOLF_HEARTS);
				player.sendSystemMessage(Component.translatable("treat." + this.type.getName() + ".level_up"));
			}

			return InteractionResult.SUCCESS;
		} else {
			if (!world.isClientSide) {
				world.broadcastEntityEvent(dog, Constants.EntityState.WOLF_SMOKE);
				player.sendSystemMessage(Component.translatable("treat." + this.type.getName() + ".max_level"));
			}

			return InteractionResult.CONSUME;
		}
	}

	public String toString() {
		return super.toString() + "[" + "maxLevel" + ":" + getMaxLevel() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "type" + "="
				+ (getType() != null ? !getType().equals(this) ? getType().toString().replaceAll("  ", "    ") : "this"
						: "null");
	}
}
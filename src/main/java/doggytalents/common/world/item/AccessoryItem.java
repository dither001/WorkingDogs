/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.world.item;

import java.util.function.Supplier;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.api.inferface.IDogItem;
import doggytalents.api.registry.Accessory;
import doggytalents.api.registry.AccessoryInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

// line 2 "../../../../world_item.ump"
public class AccessoryItem extends Item implements IDogItem {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public AccessoryItem(Properties aProperties) {
		super(aProperties);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	@Override
	// line 20 "../../../../world_item.ump"
	public InteractionResult processInteract(AbstractDog dogIn, Level worldIn, Player playerIn,
			InteractionHand handIn) {
		if (dogIn.canInteract(playerIn)
				&& dogIn.addAccessory(this.createInstance(dogIn, playerIn.getItemInHand(handIn), playerIn))) {
			dogIn.consumeItemFromStack(playerIn, playerIn.getItemInHand(handIn));
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	// line 29 "../../../../world_item.ump"
	public AccessoryInstance createInstance(AbstractDog dogIn, ItemStack stack, Player playerIn) {
		return this.type.get().getDefault();
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 10 "../../../../world_item.ump"
	public AccessoryItem(Supplier<? extends Accessory> type, Properties properties) {
		/* custom constructor */
		this(properties);
		this.type = type;
	}

// line 16 "../../../../world_item.ump"
	public Supplier<? extends Accessory> type;

}
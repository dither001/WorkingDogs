/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package doggytalents.common.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

// line 28 "../../../item_api.ump"
public interface IDyeableArmorItem extends DyeableLeatherItem {
	default int getColor(ItemStack stack) {
		CompoundTag compoundnbt = stack.getTagElement("display");
		return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color")
				: this.getDefaultColor(stack);
	}

	default int getDefaultColor(ItemStack stack) {
		return 0xFFFFFF;
	}

}
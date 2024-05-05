package doggytalents.common.item;

import java.util.function.Supplier;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.api.registry.AccessoryInstance;
import doggytalents.common.entity.accessory.DyeableAccessory;
import doggytalents.common.world.item.AccessoryItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class DyeableAccessoryItem extends AccessoryItem implements IDyeableArmorItem {

    private Supplier<? extends DyeableAccessory> accessory;

    public DyeableAccessoryItem(Supplier<? extends DyeableAccessory> accessoryIn, Properties properties) {
        super(accessoryIn, properties);
        this.accessory = accessoryIn;
    }

    @Override
    public AccessoryInstance createInstance(AbstractDog dogIn, ItemStack stack, Player playerIn) {
        return this.accessory.get().create(this.getColor(stack));
    }

}

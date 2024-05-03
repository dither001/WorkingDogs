package doggytalents.common.entity.accessory;

import java.util.function.Supplier;

import doggytalents.client.entity.render.AccessoryModelManager.Entry;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.AccessoryModelRenderEntries;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.IAccessoryHasModel;
import doggytalents.common.item.DyeableAccessoryItem;
import doggytalents.common.register.DoggyAccessoryTypes;
import net.minecraft.world.level.ItemLike;

public class LabCoat extends DyeableAccessory implements IAccessoryHasModel{

    public LabCoat(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.HEAD, itemIn);
    }

    @Override
    public Entry getRenderEntry() {
        return AccessoryModelRenderEntries.LAB_COAT;
    }
    public static class LabCoatItem extends DyeableAccessoryItem {

        public LabCoatItem(Supplier<? extends DyeableAccessory> accessoryIn, Properties properties) {
            super(accessoryIn, properties);
        }
    }
}

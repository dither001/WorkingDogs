package doggytalents.common.entity.accessory;

import java.util.function.Supplier;

import doggytalents.client.entity.render.AccessoryModelManager.Entry;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.AccessoryModelRenderEntries;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.IAccessoryHasModel;
import doggytalents.common.register.DoggyAccessoryTypes;
import net.minecraft.world.level.ItemLike;

public class FlyingCape extends DyeableAccessory implements IAccessoryHasModel {

    public FlyingCape(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.WINGS, itemIn);
        this.setAccessoryRenderType(AccessoryRenderType.MODEL);
    }

    @Override
    public Entry getRenderEntry() {
        return AccessoryModelRenderEntries.FLYING_CAPE;
    }
    
}

package doggytalents.common.entity.accessory;

import java.util.function.Supplier;

import doggytalents.api.registry.Accessory;
import doggytalents.client.entity.render.AccessoryModelManager.Entry;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.AccessoryModelRenderEntries;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.IAccessoryHasModel;
import doggytalents.common.register.DoggyAccessoryTypes;
import net.minecraft.world.level.ItemLike;


public class CrowWings extends Accessory implements IAccessoryHasModel{


    public CrowWings(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.WINGS, itemIn);
        this.setAccessoryRenderType(AccessoryRenderType.MODEL);
    }

    @Override
    public Entry getRenderEntry() {
        return AccessoryModelRenderEntries.CROW_WINGS;
    }

}

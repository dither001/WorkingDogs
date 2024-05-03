package doggytalents.common.entity.accessory;

import java.util.function.Supplier;

import doggytalents.DoggyAccessoryTypes;
import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.render.AccessoryModelManager.Entry;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.AccessoryModelRenderEntries;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.IAccessoryHasModel;
import net.minecraft.world.level.ItemLike;

public class BowTie extends DyeableAccessory implements IAccessoryHasModel {

    public BowTie(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.BOWTIE, itemIn);
    }
    
    @Override
    public byte getRenderLayer() {
        return AccessoryInstance.RENDER_TOP;
    }

    @Override
    public Entry getRenderEntry() {
        return AccessoryModelRenderEntries.BOWTIE;
    }

}

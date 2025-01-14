package doggytalents.common.entity.accessory;

import java.util.function.Supplier;

import doggytalents.api.registry.Accessory;
import doggytalents.api.registry.AccessoryInstance;
import doggytalents.common.register.DoggyAccessoryTypes;
import net.minecraft.world.level.ItemLike;

public class Band extends Accessory {

    public Band(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.BAND, itemIn);
    }

    @Override
    public byte getRenderLayer() {
        return AccessoryInstance.RENDER_TOP;
    }

    @Override
    public boolean isDogStillNakedWhenWear() {
        return true;
    }
}

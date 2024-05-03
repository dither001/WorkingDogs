package doggytalents.common.entity.accessory;

import java.util.function.Supplier;

import doggytalents.api.registry.AccessoryInstance;
import doggytalents.common.register.DoggyAccessoryTypes;
import net.minecraft.world.level.ItemLike;

public class LeatherHelmet extends DyeableAccessory {

    public LeatherHelmet(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.HEAD, itemIn);
    }

    @Override
    public byte getRenderLayer() {
        return AccessoryInstance.RENDER_TOP;
    }
}

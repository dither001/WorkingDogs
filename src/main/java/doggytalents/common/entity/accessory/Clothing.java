package doggytalents.common.entity.accessory;

import java.util.function.Supplier;

import doggytalents.api.registry.Accessory;
import doggytalents.common.register.DoggyAccessoryTypes;
import net.minecraft.world.level.ItemLike;

public class Clothing extends Accessory {

    public Clothing(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.CLOTHING, itemIn);
    }

}

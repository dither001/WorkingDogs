package doggytalents.common.item;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.common.item.food.DogEddibleBowlFoodItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class SoyMilkItem extends DogEddibleBowlFoodItem  {

    public SoyMilkItem() {
        super(
            b -> b
                .nutrition(6)
                .saturationMod(0.5F)
        );
    }

    @Override
    public boolean alwaysEatWhenDogConsume(AbstractDog dog) {
        return true;
    }

    @Override
    public SoundEvent getDogEatingSound(AbstractDog dog) {
        return SoundEvents.GENERIC_DRINK;
    }

}

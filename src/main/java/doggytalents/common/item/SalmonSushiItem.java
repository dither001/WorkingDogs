package doggytalents.common.item;

import doggytalents.common.item.food.DogEddibleItem;

public class SalmonSushiItem extends DogEddibleItem {

    public SalmonSushiItem() {
        super(
            b -> b
                .nutrition(8)
                .saturationMod(0.6F)
        );
    }
    
}

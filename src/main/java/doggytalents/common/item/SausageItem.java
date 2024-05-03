package doggytalents.common.item;

public class SausageItem extends DogEddibleItem {

    public SausageItem() {
        super(
            b -> b
                .nutrition(8)
                .saturationMod(0.6F)
        );
    }
    
}

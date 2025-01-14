package doggytalents.common.item;

import java.util.List;

import javax.annotation.Nullable;

import doggytalents.api.inferface.AbstractDog;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RiceBowlItem extends DogEddibleBowlFoodItem {
    
    public RiceBowlItem() {
        super(
            b -> b
                .nutrition(5)
                .saturationMod(0.5F)
        ); 
    }

    @Override
    public boolean alwaysEatWhenDogConsume(AbstractDog dog) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components,
            TooltipFlag flags) {
        var desc_id = this.getDescriptionId(stack) + ".description";
        components.add(Component.translatable(desc_id).withStyle(
            Style.EMPTY.withItalic(true)
        ));
    }
}

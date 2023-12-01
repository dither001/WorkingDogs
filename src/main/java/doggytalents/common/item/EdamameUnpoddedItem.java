package doggytalents.common.item;

import java.util.List;

import javax.annotation.Nullable;

import doggytalents.api.inferface.AbstractDog;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class EdamameUnpoddedItem extends Item implements IDogEddible {

    public static final FoodProperties FOOD_PROPS = 
        (new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.3F)
            .build();

    public EdamameUnpoddedItem() {
        super(
            (new Properties()).food(FOOD_PROPS)
        );
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components,
            TooltipFlag flags) {
        var desc_id = this.getDescriptionId(stack) + ".description";
        components.add(Component.translatable(desc_id).withStyle(
            Style.EMPTY.withItalic(true)
        ));
    }

    @Override
    public float getAddedHunger(ItemStack useStack, AbstractDog dog) {
        return FOOD_PROPS.getNutrition() * 5;
    }
}
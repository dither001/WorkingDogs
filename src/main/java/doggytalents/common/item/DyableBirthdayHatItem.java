package doggytalents.common.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import doggytalents.common.entity.accessory.DoubleDyableAccessory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class DyableBirthdayHatItem extends DoubleDyableAccessoryItem {
    
    public DyableBirthdayHatItem(Supplier<? extends DoubleDyableAccessory> type, Properties properties) {
        super(type, properties);
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
    public int getDefaultBgColor() {
        return 0xffffffff;
    }
    
}

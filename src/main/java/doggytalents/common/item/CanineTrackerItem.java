package doggytalents.common.item;

import javax.annotation.Nullable;

import doggytalents.client.screen.CanineTrackerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CanineTrackerItem extends Item {

    public CanineTrackerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        var stack = playerIn.getItemInHand(handIn);

        if (!worldIn.isClientSide) {
            if (stack.getItem() instanceof CanineTrackerItem && stack.hasTag()) {
                stack.setTag(null);
            }
        } else {
            if (!stack.hasTag())
                CanineTrackerScreen.open();
        }
        return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL, stack);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (stack.hasTag()) {
            var text = getStatusText(stack.getTag());
            if (text != null) return text; 
        }
        return Component.translatable(this.getDescriptionId(stack));
    }

    private @Nullable Component getStatusText(CompoundTag tag) {
        if (tag == null)
            return null;
        if (!tag.contains("name", Tag.TAG_STRING))
            return null;
        var ret = Component.translatable("item.doggytalents.radar.status", tag.getString("name"));
        int ret_color = 0xffffea2e;
        if (tag.contains("locateColor", Tag.TAG_INT)) {
            int tag_color = tag.getInt("locateColor");
            ret_color = tag_color != 0 ? tag_color : ret_color;
        }
        return ret.withStyle(
            Style.EMPTY.withColor(ret_color)
        );
    }
}

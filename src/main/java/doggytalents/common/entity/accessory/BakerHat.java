package doggytalents.common.entity.accessory;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.render.AccessoryModelManager.Entry;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.AccessoryModelRenderEntries;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.IAccessoryHasModel;
import doggytalents.common.item.DyeableAccessoryItem;
import doggytalents.common.register.DoggyAccessoryTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class BakerHat extends DyeableAccessory implements IAccessoryHasModel{

    public BakerHat(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.HEAD, itemIn);
    }
    @Override
    public byte getRenderLayer() {
        return AccessoryInstance.RENDER_TOP;
    }

    @Override
    public Entry getRenderEntry() {
        return AccessoryModelRenderEntries.BAKER_HAT;
    }
    public static class BakerHatItem extends DyeableAccessoryItem {

        public BakerHatItem(Supplier<? extends DyeableAccessory> accessoryIn, Properties properties) {
            super(accessoryIn, properties);
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
    
}

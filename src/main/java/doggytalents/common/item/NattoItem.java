package doggytalents.common.item;

import java.util.List;

import javax.annotation.Nullable;

import com.mojang.datafixers.util.Pair;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.common.register.DoggyEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class NattoItem extends DogEddibleItem {

    public NattoItem() {
        super(
            b -> b
                .nutrition(6)
                .saturationMod(0.6F)
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
    public List<Pair<MobEffectInstance, Float>> getAdditionalEffectsWhenDogConsume(ItemStack useStack,
            AbstractDog dog) {
        return List.of(Pair.of(new MobEffectInstance(DoggyEffects.NATTO_BITE.get(), 180 * 20, 1), 1f));
    }

    @Override
    public boolean alwaysEatWhenDogConsume(AbstractDog dog) {
        return true;
    }

}

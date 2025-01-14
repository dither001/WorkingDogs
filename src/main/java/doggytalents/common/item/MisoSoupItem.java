package doggytalents.common.item;

import java.util.List;

import javax.annotation.Nullable;

import doggytalents.api.inferface.AbstractDog;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MisoSoupItem extends DogEddibleBowlFoodItem {

    public MisoSoupItem() {
        super(
            b -> b
                .nutrition(10)
                .saturationMod(0.8F)
                .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 1200, 2), 1)
                .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1)
                .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1), 1)
                .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1), 1)
                .alwaysEat()
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
    public boolean alwaysEatWhenDogConsume(AbstractDog dog) {
        return true;
    }
    
    @Override
    public SoundEvent getDogEatingSound(AbstractDog dog) {
        return SoundEvents.GENERIC_DRINK;
    }
    
}

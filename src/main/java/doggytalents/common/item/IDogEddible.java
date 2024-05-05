/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package doggytalents.common.item;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.api.inferface.IDogFoodHandler;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

// line 2 "../../../item_api.ump"
public interface IDogEddible extends IDogFoodHandler {

	default boolean alwaysEatWhenDogConsume(AbstractDog dog) {
		return false;
	};

	default List<Pair<MobEffectInstance, Float>> getAdditionalEffectsWhenDogConsume(ItemStack useStack,
			AbstractDog dog) {
		return List.of();
	}

	default SoundEvent getDogEatingSound(AbstractDog dog) {
		return SoundEvents.GENERIC_EAT;
	}

	default ItemStack getReturnStackAfterDogConsume(ItemStack useStack, AbstractDog dog) {
		return ItemStack.EMPTY;
	};

	// ABSTRACT METHODS

	public float getAddedHungerWhenDogConsume(ItemStack useStack, AbstractDog dog);
}
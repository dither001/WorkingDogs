package doggytalents;

import java.util.function.Supplier;

import doggytalents.common.inventory.recipe.DogBedRecipe;
import doggytalents.common.inventory.recipe.DoubleDyableRecipe;
import doggytalents.common.lib.Constants;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DoggyRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.RECIPE_SERIALIZERS, Constants.MOD_ID);

    public static final RegistryObject<RecipeSerializer<DogBedRecipe>> DOG_BED = register("dog_bed", DogBedRecipe::new);
    public static final RegistryObject<RecipeSerializer<DoubleDyableRecipe>> DOUBLE_DYABLE = register("double_dyable", DoubleDyableRecipe::new);
//    public static final RegistryObject<SpecialRecipeSerializer<DogCollarRecipe>> COLLAR_COLOURING = register("collar_colouring", DogCollarRecipe::new);
//    public static final RegistryObject<SpecialRecipeSerializer<DogCapeRecipe>> CAPE_COLOURING = register("cape_colouring", DogCapeRecipe::new);

    private static <R extends CraftingRecipe, T extends RecipeSerializer<R>> RegistryObject<RecipeSerializer<R>> register(final String name, SimpleCraftingRecipeSerializer.Factory<R> factory) {
        return register(name, () -> new SimpleCraftingRecipeSerializer<R>(factory));
    }

    private static <T extends RecipeSerializer<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return RECIPE_SERIALIZERS.register(name, sup);
    }
}


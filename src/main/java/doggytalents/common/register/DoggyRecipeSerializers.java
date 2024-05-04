/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

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

// line 2 "../../../register_recipe.ump"
public class DoggyRecipeSerializers {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.Keys.RECIPE_SERIALIZERS, Constants.MOD_ID);
	public static final RegistryObject<RecipeSerializer<DogBedRecipe>> DOG_BED = register("dog_bed", DogBedRecipe::new);
	public static final RegistryObject<RecipeSerializer<DoubleDyableRecipe>> DOUBLE_DYABLE = register("double_dyable",
			DoubleDyableRecipe::new);

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyRecipeSerializers() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 10 "../../../register_recipe.ump"
	private static <R extends CraftingRecipe, T extends RecipeSerializer<R>> RegistryObject<RecipeSerializer<R>> register(
			final String name, SimpleCraftingRecipeSerializer.Factory<R> factory) {
		return register(name, () -> new SimpleCraftingRecipeSerializer<R>(factory));
	}

// line 14 "../../../register_recipe.ump"
	private static <T extends RecipeSerializer<?>> RegistryObject<T> register(final String name,
			final Supplier<T> sup) {
		return RECIPE_SERIALIZERS.register(name, sup);
	}

}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.ArrayList;
import java.util.function.Supplier;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

// line 2 "../../../register_brewing.ump"
public class DoggyBrewingRecipes {
	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	private static final ArrayList<Supplier<BrewingRecipe>> RECIPES = new ArrayList<>();

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final Supplier<BrewingRecipe> SAKE_BREW = register(() -> new BrewingRecipe(
			Ingredient.of(Items.POTION), Ingredient.of(DoggyItems.KOJI.get()), new ItemStack(DoggyItems.SAKE.get())));

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyBrewingRecipes() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 12 "../../../register_brewing.ump"
	private static Supplier<BrewingRecipe> register(Supplier<BrewingRecipe> recipe) {
		RECIPES.add(recipe);
		return recipe;
	}

	// line 17 "../../../register_brewing.ump"
	public static void registerAll() {
		for (var x : RECIPES) {
			BrewingRecipeRegistry.addRecipe(x.get());
		}
	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
}
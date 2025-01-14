/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.data.recipe;

import java.util.function.Consumer;

import doggytalents.common.register.DoggyBlocks;
import doggytalents.common.register.DoggyItems;
import doggytalents.common.register.DoggyRecipeSerializers;
import doggytalents.common.util.Util;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

// line 2 "../../../../data_recipe.ump"
public class DTRecipeProvider extends RecipeProvider {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DTRecipeProvider(PackOutput aOutput) {
		super(aOutput);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	@Override
	// line 11 "../../../../data_recipe.ump"
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.THROW_BONE.get()).pattern(" X ").pattern("XYX")
				.pattern(" X ").define('X', Items.BONE).define('Y', Items.SLIME_BALL)
				.unlockedBy("has_bone", has(Items.BONE)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, DoggyItems.THROW_BONE.get())
				.requires(DoggyItems.THROW_BONE_WET.get(), 1)
				.unlockedBy("has_throw_bone", has(DoggyItems.THROW_BONE.get()))
				.save(consumer, Util.getResource("throw_bone_wet"));

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.THROW_STICK.get(), 1).pattern(" X ").pattern("XYX")
				.pattern(" X ").define('X', Items.STICK).define('Y', Items.SLIME_BALL)
				.unlockedBy("has_slime_ball", has(Items.SLIME_BALL)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, DoggyItems.THROW_STICK.get(), 1)
				.requires(DoggyItems.THROW_STICK_WET.get(), 1)
				.unlockedBy("has_throw_stick", has(DoggyItems.THROW_STICK.get()))
				.save(consumer, Util.getResource("throw_stick_wet"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.SUPER_TREAT.get(), 5)
				.requires(DoggyItems.TRAINING_TREAT.get(), 5).requires(Items.GOLDEN_APPLE, 1)
				.unlockedBy("has_golden_apple", has(Items.GOLDEN_APPLE)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.KAMI_TREAT.get(), 1)
				.requires(DoggyItems.MASTER_TREAT.get(), 5).requires(Blocks.END_STONE, 1)
				.unlockedBy("has_master_treat", has(DoggyItems.MASTER_TREAT.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.BREEDING_BONE.get(), 2)
				.requires(DoggyItems.MASTER_TREAT.get(), 1).requires(Items.COOKED_BEEF, 1)
				.requires(Items.COOKED_PORKCHOP, 1).requires(Items.COOKED_CHICKEN, 1).requires(Items.COOKED_COD, 1)
				.unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.MASTER_TREAT.get(), 5)
				.requires(DoggyItems.SUPER_TREAT.get(), 5).requires(Items.DIAMOND, 1)
				.unlockedBy("has_master_treat", has(DoggyItems.SUPER_TREAT.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.TRAINING_TREAT.get(), 3).requires(Items.STRING)
				.requires(Items.BONE).requires(Items.GUNPOWDER).requires(Items.SUGAR)
				.requires(DoggyItems.RICE_BOWL.get())
				.unlockedBy("has_dtn_rice_grains", has(DoggyItems.RICE_GRAINS.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.WHISTLE.get(), 1).pattern("IRI").pattern("II ")
				.define('I', Items.IRON_INGOT).define('R', Items.REDSTONE)
				.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DoggyBlocks.FOOD_BOWL.get(), 1).pattern("XXX")
				.pattern("XYX").pattern("XXX").define('X', Items.IRON_INGOT).define('Y', Items.BOWL)
				.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DoggyBlocks.DOG_BATH.get(), 1).pattern("XXX")
				.pattern("XYX").pattern("XXX").define('X', Items.IRON_INGOT).define('Y', Items.WATER_BUCKET)
				.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.WOOL_COLLAR.get(), 1).pattern("SSS")
				.pattern("S S").pattern("SSS").define('S', Items.STRING).unlockedBy("has_string", has(Items.STRING))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.WOOL_COLLAR_THICC.get(), 1)
				.requires(DoggyItems.WOOL_COLLAR.get(), 2).unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.TREAT_BAG.get(), 1).pattern("LCL").pattern("LLL")
				.define('L', Items.LEATHER).define('C', DoggyItems.ENERGIZER_STICK.get())
				.unlockedBy("has_leather", has(Items.LEATHER)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.GUARD_SUIT.get(), 1).pattern("S S")
				.pattern("BWB").pattern("BWB").define('S', Items.STRING).define('W', Blocks.WHITE_WOOL)
				.define('B', Blocks.BLACK_WOOL).unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.LEATHER_JACKET.get(), 1).pattern("L L")
				.pattern("LWL").pattern("LWL").define('L', Items.LEATHER).define('W', ItemTags.WOOL)
				.unlockedBy("has_leather", has(Items.LEATHER)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.SPOTTED_COLLAR.get(), 1).pattern("BWB")
				.pattern("WCW").pattern("BSB").define('C', DoggyItems.WOOL_COLLAR.get()).define('B', Items.BLACK_DYE)
				.define('W', Items.WHITE_DYE).define('S', Items.STRING)
				.unlockedBy("has_wool_collar", has(DoggyItems.WOOL_COLLAR.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.SPOTTED_COLLAR.get(), 1).pattern("WBW")
				.pattern("BCB").pattern("WSW").define('C', DoggyItems.WOOL_COLLAR.get()).define('B', Items.BLACK_DYE)
				.define('W', Items.WHITE_DYE).define('S', Items.STRING)
				.unlockedBy("has_wool_collar", has(DoggyItems.WOOL_COLLAR.get()))
				.save(consumer, Util.getResource("spotted_collar_alt"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.MULTICOLOURED_COLLAR.get(), 1)
				.requires(DoggyItems.WOOL_COLLAR.get()).requires(Items.STRING).requires(Items.BLUE_DYE)
				.requires(Items.LIME_DYE).requires(Items.YELLOW_DYE).requires(Items.ORANGE_DYE).requires(Items.RED_DYE)
				.requires(Items.PURPLE_DYE).unlockedBy("has_wool_collar", has(DoggyItems.WOOL_COLLAR.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.PIANIST_SUIT.get(), 1).pattern("GWG")
				.pattern("GDG").pattern("BWB").define('G', Blocks.GRAY_WOOL).define('D', DoggyItems.CAPE_COLOURED.get())
				.define('W', Blocks.WHITE_WOOL).define('B', Blocks.BLACK_WOOL)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DoggyItems.AMNESIA_BONE.get(), 1).pattern(" RN").pattern("WBR")
				.pattern("SW ").define('S', Items.SOUL_SOIL).define('W', Items.NETHER_WART).define('B', Items.BONE)
				.define('R', Items.BLAZE_ROD).define('N', Items.NETHERITE_INGOT)
				.unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.ENERGIZER_STICK.get(), 1).pattern("SW").pattern("WS")
				.define('W', Items.WHEAT).define('S', Items.SUGAR).unlockedBy("has_sugar", has(Items.SUGAR))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.EGG_SANDWICH.get(), 3).pattern(" B ").pattern("EEE")
				.pattern(" B ").define('B', Items.BREAD).define('E', Items.EGG).unlockedBy("has_egg", has(Items.EGG))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.TANTAN_CAPE.get(), 1).pattern("S S")
				.pattern("RBR").pattern("BYB").define('R', Items.RED_WOOL).define('S', Items.STRING)
				.define('B', Items.BLUE_WOOL).define('Y', Items.YELLOW_WOOL)
				.unlockedBy("has_leather", has(Items.LEATHER)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.CAPE_COLOURED.get(), 1).pattern("S S")
				.pattern("LWL").pattern("WLW").define('L', Items.LEATHER).define('W', Items.WHITE_WOOL)
				.define('S', Items.STRING).unlockedBy("has_leather", has(Items.LEATHER)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.BOWTIE.get(), 4).pattern("W W").pattern("WSW")
				.define('W', Blocks.WHITE_WOOL).define('S', Items.STRING).unlockedBy("has_string", has(Items.STRING))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.WIG.get(), 1).pattern(" W ").pattern("WCW")
				.pattern(" W ").define('W', Blocks.WHITE_WOOL).define('C', DoggyItems.WOOL_COLLAR.get())
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.BACH_WIG.get(), 1).pattern("SSS")
				.pattern("SBS").pattern("S S").define('S', Items.STRING).define('B', Items.BONE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.SMARTY_GLASSES.get(), 1)
				.requires(DoggyItems.SUNGLASSES.get()).requires(Items.REDSTONE)
				.unlockedBy("has_redstone", has(Items.REDSTONE)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.DEATH_HOOD.get(), 1)
				.requires(DoggyItems.CAPE_COLOURED.get()).requires(Items.SOUL_TORCH).requires(Items.NETHER_WART)
				.unlockedBy("has_soul_torch", has(Items.SOUL_TORCH)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.SUNGLASSES.get(), 1).pattern("S S")
				.pattern("GSG").define('S', Items.STICK).define('G', Blocks.BLACK_STAINED_GLASS_PANE)
				.unlockedBy("has_stick", has(Items.STICK)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.RADIO_COLLAR.get(), 1).pattern("XX").pattern("YX")
				.define('X', Items.IRON_INGOT).define('Y', Items.REDSTONE)
				.unlockedBy("has_redstone", has(Items.REDSTONE)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.CONAN_SUIT.get(), 1).pattern("BZB")
				.pattern("LCL").pattern("R R").define('L', Items.LIGHT_BLUE_WOOL).define('Z', DoggyItems.BOWTIE.get())
				.define('R', Items.RED_WOOL).define('B', Items.BLUE_WOOL).define('C', DoggyItems.CAPE_COLOURED.get())
				.unlockedBy("has_wool", has(Items.WHITE_WOOL)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.BEASTARS_UNIFORM_FEMALE.get(), 1)
				.pattern("WBW").pattern("WLW").pattern("CCC").define('W', Items.WHITE_WOOL)
				.define('B', DoggyItems.BOWTIE.get()).define('C', Items.WHITE_CARPET)
				.define('L', DoggyItems.CAPE_COLOURED.get()).unlockedBy("has_wool", has(Items.WHITE_WOOL))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.BEASTARS_UNIFORM_MALE.get(), 1).pattern("BCB")
				.pattern("WGW").pattern("R R").define('B', Items.BLUE_WOOL).define('W', Items.WHITE_WOOL)
				.define('R', Items.BROWN_WOOL).define('G', Items.GRAY_WOOL).define('C', DoggyItems.CAPE_COLOURED.get())
				.unlockedBy("has_wool", has(Items.WHITE_WOOL)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.CONDUCTING_BONE.get(), 1).pattern(" B ")
				.pattern("RBR").pattern("PEP").define('P', Items.SHULKER_SHELL).define('E', Items.END_CRYSTAL)
				.define('B', Items.BONE).define('R', Items.NETHERITE_INGOT).unlockedBy("has_bone", has(Items.BONE))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.SHRINKING_MALLET.get(), 1).pattern("GGG")
				.pattern("GFG").pattern(" B ").define('G', Items.GOLD_INGOT).define('F', Items.REDSTONE_BLOCK)
				.define('B', Items.BONE).unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.MAGNIFYING_BONE.get(), 1).pattern(" GF")
				.pattern("GPG").pattern("BG ").define('G', Items.GOLD_INGOT).define('F', Items.REDSTONE_BLOCK)
				.define('B', Items.BONE).define('P', Items.GLASS_PANE)
				.unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.CANINE_TRACKER.get(), 1).pattern(" GC")
				.pattern("GMG").pattern(" G ").define('G', Items.GOLD_INGOT).define('C', DoggyItems.RADIO_COLLAR.get())
				.define('M', Items.MAP).unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, DoggyItems.BANDAID.get(), 5).requires(Items.PAPER, 2)
				.requires(DoggyItems.KOJI.get()).requires(Items.BONE_MEAL)
				.unlockedBy("has_koji", has(DoggyItems.KOJI.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.FEATHERED_MANTLE.get(), 1).pattern(" FP")
				.pattern("FTF").pattern("PF ").define('F', Items.FEATHER).define('P', Items.PHANTOM_MEMBRANE)
				.define('T', DoggyItems.SUPER_TREAT.get()).unlockedBy("has_feather", has(Items.FEATHER)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.EMPTY_LOCATOR_ORB.get(), 1).pattern(" C ")
				.pattern("SRS").pattern(" G ").define('C', Items.WHITE_CARPET).define('G', Items.GLASS)
				.define('R', DoggyItems.RADIO_COLLAR.get()).define('S', Items.STRING)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.CHI_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.MAGENTA_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.CHU_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.LIGHT_BLUE_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.KO_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.PURPLE_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.GI_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.BLUE_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.TEI_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.YELLOW_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.REI_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.LIME_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.SHIN_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.GREEN_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.JIN_ORB.get(), 1)
				.requires(DoggyItems.EMPTY_LOCATOR_ORB.get()).requires(Items.RED_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DoggyItems.GENDER_BONE.get(), 1).pattern(" LB").pattern("MAL")
				.pattern("PM ").define('L', Items.LAPIS_LAZULI).define('B', Items.BLUE_DYE)
				.define('A', DoggyItems.AMNESIA_BONE.get()).define('P', Items.PINK_DYE)
				.define('M', Items.AMETHYST_SHARD).unlockedBy("has_string", has(Items.STRING)).save(consumer);

		SpecialRecipeBuilder.special(DoggyRecipeSerializers.DOG_BED.get()).save(consumer,
				Util.getResourcePath("dog_bed"));
		SpecialRecipeBuilder.special(DoggyRecipeSerializers.DOUBLE_DYABLE.get()).save(consumer,
				Util.getResourcePath("birthday_hat"));

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.HOT_DOG.get(), 1).pattern("RTY")
				.pattern("BCB").define('R', Items.RED_DYE).define('Y', Items.YELLOW_DYE).define('B', Items.BREAD)
				.define('C', DoggyItems.SAUSAGE.get()).define('T', DoggyItems.TRAINING_TREAT.get())
				.unlockedBy("has_bread", has(Items.BREAD)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.GIANT_STICK.get(), 1).pattern(" S ")
				.pattern(" S ").pattern(" S ").define('S', Items.STICK).unlockedBy("has_stick", has(Items.STICK))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.GOLDEN_A_FIVE_WAGYU.get(), 1).pattern(" GT")
				.pattern("GSG").pattern(" G ").define('G', Items.GOLD_NUGGET).define('S', Items.COOKED_BEEF)
				.define('T', DoggyItems.TRAINING_TREAT.get()).unlockedBy("has_cooked_beef", has(Items.COOKED_BEEF))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DoggyItems.SUSSY_SICKLE.get(), 1).pattern("III")
				.pattern(" SI").pattern("S  ").define('I', Items.IRON_INGOT).define('S', Items.STICK)
				.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.SNORKEL.get(), 1).pattern("  B")
				.pattern("GIG").define('I', Items.IRON_INGOT).define('G', Items.GLASS_PANE).define('B', Items.BAMBOO)
				.unlockedBy("has_bamboo", has(Items.BAMBOO)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.HEAD_BAND_BLANK.get(), 1).pattern("L")
				.pattern("P").define('L', Items.LEAD).define('P', Items.PAPER).unlockedBy("has_paper", has(Items.PAPER))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.FRISBEE.get(), 1).pattern("SBS")
				.define('S', ItemTags.SLABS).define('B', DoggyItems.THROW_BONE.get())
				.unlockedBy("has_slime_ball", has(Items.SLIME_BALL)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.SAUSAGE.get(), 3).pattern("SSS")
				.define('S', Items.COOKED_PORKCHOP).unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.KITSUNE_MASK.get(), 1)
				.requires(DoggyItems.HEAD_BAND_BLANK.get()).requires(Items.RED_DYE).requires(Items.WHITE_DYE, 2)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.TENGU_MASK.get(), 1)
				.requires(DoggyItems.HEAD_BAND_BLANK.get()).requires(Items.RED_DYE, 2).requires(Items.BLACK_DYE)
				.unlockedBy("has_string", has(Items.STRING)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.BAKER_HAT.get(), 1).pattern("WWW")
				.pattern("WCW").define('C', Items.LEATHER_HELMET).define('W', ItemTags.WOOL)
				.unlockedBy("has_bread", has(Items.BREAD)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.CHEF_HAT.get(), 1).pattern("WWW")
				.pattern("WWW").pattern("WCW").define('C', Items.LEATHER_HELMET).define('W', ItemTags.WOOL)
				.unlockedBy("has_leather_helmet", has(Items.LEATHER_HELMET)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.DEMON_HORNS.get(), 1).pattern("SRS")
				.pattern("BCB").define('C', Items.LEATHER_HELMET).define('S', Items.STICK)
				.define('R', Items.NETHER_WART).define('B', Items.BLACK_DYE)
				.unlockedBy("has_leather_helmet", has(Items.LEATHER_HELMET)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.UNCOOKED_RICE_BOWL.get(), 1)
				.requires(Items.BOWL).requires(DoggyItems.UNCOOKED_RICE.get(), 5)
				.unlockedBy("has_dtn_rice_grains", has(DoggyItems.RICE_GRAINS.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.PLAGUE_DOC_MASK.get(), 1).pattern("CWC")
				.pattern(" S ").pattern("LFL").define('C', Items.BLACK_CARPET).define('W', Items.BLACK_WOOL)
				.define('L', Items.LEATHER).define('F', ItemTags.FLOWERS).define('S', DoggyItems.SNORKEL.get())
				.unlockedBy("has_leather", has(Items.LEATHER)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.SALMON_SUSHI.get(), 1).pattern("S").pattern("R")
				.define('S', Items.SALMON).define('R', DoggyItems.RICE_BOWL.get())
				.unlockedBy("has_rice_bowl", has(DoggyItems.RICE_BOWL.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.CROW_WINGS.get(), 1).pattern("FBF")
				.pattern("BEB").pattern("FBF").define('F', Items.FEATHER).define('B', Items.BLACK_DYE)
				.define('E', Items.ELYTRA).unlockedBy("has_elytra", has(Items.ELYTRA)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.CROW_WINGS.get(), 1).pattern("FBF")
				.pattern("BEB").pattern("FBF").define('B', Items.FEATHER).define('F', Items.BLACK_DYE)
				.define('E', Items.ELYTRA).unlockedBy("has_elytra", has(Items.ELYTRA))
				.save(consumer, Util.getResource("crow_wings_alt"));

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.FLYING_CAPE.get(), 1).pattern(" W ")
				.pattern("WEW").pattern(" W ").define('W', ItemTags.WOOL).define('E', Items.ELYTRA)
				.unlockedBy("has_elytra", has(Items.ELYTRA)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DoggyItems.SUPERDOG_SUIT.get(), 1)
				.requires(Items.YELLOW_DYE).requires(Items.RED_DYE).requires(Items.BLUE_DYE).requires(Items.IRON_INGOT)
				.requires(DoggyItems.LEATHER_JACKET.get()).unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.BAT_WINGS.get(), 1).pattern("BLB")
				.pattern("LEL").pattern("BLB").define('B', Items.BROWN_DYE).define('L', Items.LEATHER)
				.define('E', Items.ELYTRA).unlockedBy("has_elytra", has(Items.ELYTRA)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.WITCH_HAT.get(), 1).pattern(" W ")
				.pattern("WPW").pattern("CCC").define('W', Items.BLACK_WOOL).define('C', Items.BLACK_CARPET)
				.define('P', Items.POTION).unlockedBy("has_potion", has(Items.POTION)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.ONIGIRI.get(), 1).pattern("R").pattern("K")
				.define('R', DoggyItems.RICE_BOWL.get()).define('K', Items.DRIED_KELP)
				.unlockedBy("has_rice_bowl", has(DoggyItems.RICE_BOWL.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.DIVINE_RETRIBUTON.get(), 1).pattern("MPM")
				.pattern("CXC").pattern("BPB").define('C', Items.COPPER_INGOT).define('P', Items.PHANTOM_MEMBRANE)
				.define('M', Items.MAGMA_CREAM).define('B', Items.BLAZE_POWDER).define('X', DoggyItems.SAKE.get())
				.unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.SOUL_REFLECTOR.get(), 1).pattern(" S ")
				.pattern("SXS").pattern(" S ").define('S', Items.SOUL_SAND)
				.define('X', DoggyItems.DIVINE_RETRIBUTON.get()).unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.LAB_COAT.get(), 1).pattern(" C ")
				.pattern(" W ").pattern("WWW").define('C', DoggyItems.CAPE_COLOURED.get()).define('W', ItemTags.WOOL)
				.unlockedBy("has_wool", has(ItemTags.WOOL)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.BIRTHDAY_HAT.get(), 1).pattern(" P ")
				.pattern("PTP").define('T', DoggyItems.TRAINING_TREAT.get()).define('P', Items.PAPER)
				.unlockedBy("has_paper", has(Items.PAPER)).save(consumer, Util.getResource("birthday_hat_alt"));

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DoggyBlocks.DOG_BED.get(), 1).pattern("WDW")
				.pattern("WDW").pattern("WWW").define('W', Blocks.SPRUCE_PLANKS).define('D', Blocks.WHITE_WOOL)
				.unlockedBy("has_wool", has(ItemTags.WOOL)).save(consumer, Util.getResource("dog_bed_def"));

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.SOY_MILK.get(), 1).pattern("SSS").pattern("SBS")
				.define('S', DoggyItems.SOY_BEANS_DRIED.get()).define('B', Items.BOWL)
				.unlockedBy("has_dtn_soy_beans", has(DoggyItems.SOY_BEANS.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.TOFU.get(), 8).pattern("SSS").pattern("SSS")
				.define('S', DoggyItems.SOY_MILK.get()).unlockedBy("has_dtn_soy_beans", has(DoggyItems.SOY_BEANS.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.MISO_PASTE.get(), 1)
				.requires(DoggyItems.SOY_BEANS.get()).requires(DoggyItems.KOJI.get()).requires(Items.RED_MUSHROOM)
				.unlockedBy("has_dtn_soy_beans", has(DoggyItems.SOY_BEANS.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.NATTO.get(), 1)
				.requires(DoggyItems.SOY_BEANS.get()).requires(DoggyItems.KOJI.get()).requires(Items.BROWN_MUSHROOM)
				.unlockedBy("has_dtn_soy_beans", has(DoggyItems.SOY_BEANS.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.KOJI.get(), 3).requires(DoggyItems.KOJI.get())
				.requires(DoggyItems.UNCOOKED_RICE.get()).requires(Items.SUGAR)
				.unlockedBy("has_dtn_koji", has(DoggyItems.KOJI.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.MISO_SOUP.get(), 1)
				.requires(DoggyItems.MISO_PASTE.get()).requires(DoggyItems.TOFU.get()).requires(Items.BOWL)
				.requires(Items.DRIED_KELP).unlockedBy("has_dtn_soy_beans", has(DoggyItems.SOY_BEANS.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.EDAMAME.get(), 3)
				.requires(DoggyItems.SOY_PODS.get(), 3).requires(Items.SUGAR)
				.unlockedBy("has_dtn_soy_pods", has(DoggyItems.SOY_PODS.get())).save(consumer);

		registerTripleCooking(consumer, Ingredient.of(DoggyItems.SOY_PODS.get()), DoggyItems.SOY_PODS_DRIED.get(),
				0.35F, 200, "has_dtn_soy_pods", has(DoggyItems.SOY_PODS.get()));

		registerTripleCooking(consumer, Ingredient.of(DoggyItems.SOY_BEANS.get()), DoggyItems.SOY_BEANS_DRIED.get(),
				0.1F, 100, "has_dtn_soy_beans", has(DoggyItems.SOY_BEANS.get()));

		registerTripleCooking(consumer, Ingredient.of(DoggyItems.UNCOOKED_RICE_BOWL.get()), DoggyItems.RICE_BOWL.get(),
				0.1F, 100, "has_dtn_uncooked_rice_bowl", has(DoggyItems.UNCOOKED_RICE_BOWL.get()));

		registerTripleCooking(consumer, Ingredient.of(DoggyItems.TOFU.get()), DoggyItems.ABURAAGE.get(), 0.1F, 100,
				"has_dtn_tofu", has(DoggyItems.TOFU.get()));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DoggyItems.RICE_GRAINS.get(), 3)
				.requires(DoggyItems.RICE_WHEAT.get())
				.unlockedBy("has_dtn_rice_wheat", has(DoggyItems.RICE_WHEAT.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.GYUDON.get(), 1).pattern("BOB").pattern(" R ")
				.define('R', DoggyItems.RICE_BOWL.get()).define('B', Items.COOKED_BEEF)
				.define('O', DoggyItems.ONSEN_TAMAGO.get()).unlockedBy("has_rice_bowl", has(DoggyItems.RICE_BOWL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.OYAKODON.get(), 1).pattern("COC").pattern(" R ")
				.define('R', DoggyItems.RICE_BOWL.get()).define('C', Items.EGG).define('O', Items.COOKED_CHICKEN)
				.unlockedBy("has_rice_bowl", has(DoggyItems.RICE_BOWL.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DoggyItems.NATTO_RICE.get(), 1).pattern("N").pattern("B")
				.define('N', DoggyItems.NATTO.get()).define('B', DoggyItems.RICE_BOWL.get())
				.unlockedBy("has_rice_bowl", has(DoggyItems.RICE_BOWL.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.CERE_GARB.get(), 1).pattern(" L ")
				.pattern("CWC").pattern(" C ").define('L', Items.LEAD).define('W', DoggyItems.WOOL_COLLAR.get())
				.define('C', ItemTags.WOOL_CARPETS).unlockedBy("has_lead", has(Items.LEAD)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.DOGGY_CONTACTS.get(), 1).pattern("GKG")
				.define('G', Items.GLASS_PANE).define('K', DoggyItems.SAKE.get())
				.unlockedBy("has_dtn_koji", has(DoggyItems.KOJI.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DoggyItems.SCENT_TREAT.get(), 1).pattern(" W ").pattern("WTW")
				.pattern(" W ").define('W', ItemTags.WOOL).define('T', DoggyItems.TRAINING_TREAT.get())
				.unlockedBy("has_dtn_training_treat", has(DoggyItems.TRAINING_TREAT.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.FEDORA.get(), 1).pattern(" BW").pattern("WCW")
				.pattern(" T ").define('B', Items.BLACK_WOOL).define('W', Items.BROWN_WOOL)
				.define('C', Items.LEATHER_HELMET).define('T', DoggyItems.TRAINING_TREAT.get())
				.unlockedBy("has_leather_helmet", has(Items.LEATHER_HELMET)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.FLATCAP.get(), 1).pattern("  C")
				.pattern("CTC").define('C', Items.WHITE_CARPET).define('T', DoggyItems.TRAINING_TREAT.get())
				.unlockedBy("has_white_carpet", has(Items.WHITE_CARPET)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.PROPELLER_HAT.get(), 1).pattern("BBB")
				.pattern("RIL").pattern("GTY").define('B', Items.BAMBOO).define('G', Items.GREEN_WOOL)
				.define('L', Items.BLUE_WOOL).define('R', Items.RED_WOOL).define('Y', Items.YELLOW_WOOL)
				.define('I', Items.IRON_INGOT).define('T', DoggyItems.TRAINING_TREAT.get())
				.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DoggyItems.DOG_PLUSHIE_TOY.get(), 1).pattern("WW ")
				.pattern("SCG").pattern("WTW").define('G', Items.LIGHT_GRAY_WOOL).define('W', Items.WHITE_WOOL)
				.define('S', Items.STRING).define('C', DoggyItems.WOOL_COLLAR.get())
				.define('T', DoggyItems.TRAINING_TREAT.get()).unlockedBy("has_white_wool", has(Items.WHITE_WOOL))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DoggyBlocks.RICE_MILL.get(), 1).pattern("FLF").pattern("LCL")
				.pattern("FDF").define('F', ItemTags.FENCES).define('L', ItemTags.LOGS)
				.define('C', Items.COBBLESTONE_SLAB).define('D', DoggyItems.RICE_WHEAT.get())
				.unlockedBy("has_paddy_rice_dtn", has(DoggyItems.RICE_WHEAT.get())).save(consumer);
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 460 "../../../../data_recipe.ump"
	private void registerTripleCooking(Consumer<FinishedRecipe> consumer, Ingredient input, Item output, float xp,
			int lengthTicks, String unlockedByStr, InventoryChangeTrigger.TriggerInstance trigger) {
		var baseNameId = ForgeRegistries.ITEMS.getKey(output).getPath();
		SimpleCookingRecipeBuilder.smelting(input, RecipeCategory.FOOD, output, xp, lengthTicks)
				.unlockedBy(unlockedByStr, trigger).save(consumer, Util.getResource(baseNameId + "_smelting"));

		SimpleCookingRecipeBuilder.campfireCooking(input, RecipeCategory.FOOD, output, xp, lengthTicks)
				.unlockedBy(unlockedByStr, trigger).save(consumer, Util.getResource(baseNameId + "_camping"));

		SimpleCookingRecipeBuilder.smoking(input, RecipeCategory.FOOD, output, xp, lengthTicks / 2)
				.unlockedBy(unlockedByStr, trigger).save(consumer, Util.getResource(baseNameId) + "_smoking");
	}

}
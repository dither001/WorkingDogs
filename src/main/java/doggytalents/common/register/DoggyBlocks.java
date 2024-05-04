/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import doggytalents.common.block.DogBathBlock;
import doggytalents.common.block.DogBedBlock;
import doggytalents.common.block.FoodBowlBlock;
import doggytalents.common.block.RiceMillBlock;
import doggytalents.common.block.crops.RiceCropBlock;
import doggytalents.common.block.crops.SoyCropBlock;
import doggytalents.common.lib.Constants;
import doggytalents.common.util.Util;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_block.ump"
public class DoggyBlocks {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.Keys.BLOCKS,
			Constants.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DoggyItems.ITEMS;
	public static final RegistryObject<DogBedBlock> DOG_BED = registerWithItem("dog_bed", DogBedBlock::new,
			(prop) -> prop);
	public static final RegistryObject<DogBathBlock> DOG_BATH = registerWithItem("dog_bath", DogBathBlock::new);
	public static final RegistryObject<FoodBowlBlock> FOOD_BOWL = registerWithItem("food_bowl", FoodBowlBlock::new);
	public static final RegistryObject<RiceMillBlock> RICE_MILL = registerWithItem("rice_mill", RiceMillBlock::new);
	public static final RegistryObject<RiceCropBlock> RICE_CROP = register("rice_crop", RiceCropBlock::new);
	public static final RegistryObject<SoyCropBlock> SOY_CROP = register("soy_crop", SoyCropBlock::new);

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyBlocks() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 32 "../../../register_block.ump"
	public static void logError() {
		// Only try to register if blocks were successfully registered
		// Trying to avoid as reports like DoggyTalents#242, where it says
		// DoggyTalents crashed but is not the CAUSE of the crash

		DoggyTalentsNext.LOGGER
				.info("Items/Blocks were not registered for some reason... probably beacuse we are c...r..a..s.hing");
	}

	/**
	 * 
	 */
	// line 42 "../../../register_block.ump"
	private static Item.Properties createInitialProp() {
		return new Item.Properties();
	}

	// line 46 "../../../register_block.ump"
	private static BlockItem makeItemBlock(Block block) {
		return makeItemBlock(block, null);
	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 21 "../../../register_block.ump"
	public static void registerBlockColours(final RegisterColorHandlersEvent.Block event) {
		BlockColors blockColors = event.getBlockColors();

		Util.acceptOrElse(DoggyBlocks.DOG_BATH, (block) -> {
			blockColors.register((state, world, pos, tintIndex) -> {
				return world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1;
			}, block);
		}, DoggyBlocks::logError);
	}

// line 49 "../../../register_block.ump"
	private static BlockItem makeItemBlock(Block block,
			@Nullable Function<Item.Properties, Item.Properties> extraPropFunc) {
		Item.Properties prop = createInitialProp();
		return new BlockItem(block, extraPropFunc != null ? extraPropFunc.apply(prop) : prop);
	}

// line 55 "../../../register_block.ump"
	private static <T extends Block> RegistryObject<T> registerWithItem(final String name,
			final Supplier<T> blockSupplier, @Nullable Function<Item.Properties, Item.Properties> extraPropFunc) {
		return register(name, blockSupplier, (b) -> makeItemBlock(b.get(), extraPropFunc));
	}

// line 60 "../../../register_block.ump"
	private static <T extends Block> RegistryObject<T> registerWithItem(final String name,
			final Supplier<T> blockSupplier) {
		return register(name, blockSupplier, (b) -> makeItemBlock(b.get()));
	}

// line 65 "../../../register_block.ump"
	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<T> blockSupplier,
			final Function<RegistryObject<T>, Item> itemFunction) {
		RegistryObject<T> blockObj = register(name, blockSupplier);
		ITEMS.register(name, () -> itemFunction.apply(blockObj));
		return blockObj;
	}

// line 72 "../../../register_block.ump"
	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<T> blockSupplier) {
		return BLOCKS.register(name, blockSupplier);
	}

}
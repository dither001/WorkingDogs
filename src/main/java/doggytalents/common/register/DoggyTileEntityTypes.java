/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.function.Supplier;

import doggytalents.common.block.tileentity.DogBedTileEntity;
import doggytalents.common.block.tileentity.FoodBowlTileEntity;
import doggytalents.common.block.tileentity.RiceMillBlockEntity;
import doggytalents.common.lib.Constants;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_blockentity.ump"
public class DoggyTileEntityTypes {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, Constants.MOD_ID);
	public static final RegistryObject<BlockEntityType<DogBedTileEntity>> DOG_BED = register("dog_bed",
			DogBedTileEntity::new, DoggyBlocks.DOG_BED);
	public static final RegistryObject<BlockEntityType<FoodBowlTileEntity>> FOOD_BOWL = register("food_bowl",
			FoodBowlTileEntity::new, DoggyBlocks.FOOD_BOWL);
	public static final RegistryObject<BlockEntityType<RiceMillBlockEntity>> RICE_MILL = register("rice_mill",
			RiceMillBlockEntity::new, DoggyBlocks.RICE_MILL);

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyTileEntityTypes() {
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

	// line 12 "../../../register_blockentity.ump"
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(final String name,
			final BlockEntityType.BlockEntitySupplier<T> sup, Supplier<? extends Block> validBlock) {
		return register(name, () -> BlockEntityType.Builder.of(sup, validBlock.get()).build(null));
	}

// line 17 "../../../register_blockentity.ump"
	private static <T extends BlockEntityType<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
		return TILE_ENTITIES.register(name, sup);
	}

}
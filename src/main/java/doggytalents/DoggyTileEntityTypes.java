package doggytalents;

import java.util.function.Supplier;

import doggytalents.common.block.tileentity.DogBedTileEntity;
import doggytalents.common.block.tileentity.FoodBowlTileEntity;
import doggytalents.common.block.tileentity.RiceMillBlockEntity;
import doggytalents.common.lib.Constants;
import doggytalents.common.register.DoggyBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//import doggytalents.common.block.tileentity.DogBedTileEntity;
//import doggytalents.common.block.tileentity.FoodBowlTileEntity;
//import doggytalents.common.block.tileentity.RiceMillBlockEntity;
//import doggytalents.common.lib.Constants;
//import doggytalents.common.register.DoggyBlocks;
//
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;

public class DoggyTileEntityTypes {

	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, Constants.MOD_ID);

	public static final RegistryObject<BlockEntityType<DogBedTileEntity>> DOG_BED = register("dog_bed",
			DogBedTileEntity::new, DoggyBlocks.DOG_BED);
	public static final RegistryObject<BlockEntityType<FoodBowlTileEntity>> FOOD_BOWL = register("food_bowl",
			FoodBowlTileEntity::new, DoggyBlocks.FOOD_BOWL);
	public static final RegistryObject<BlockEntityType<RiceMillBlockEntity>> RICE_MILL = register("rice_mill",
			RiceMillBlockEntity::new, DoggyBlocks.RICE_MILL);

	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(final String name,
			final BlockEntityType.BlockEntitySupplier<T> sup, Supplier<? extends Block> validBlock) {
		return register(name, () -> BlockEntityType.Builder.of(sup, validBlock.get()).build(null));
	}

	private static <T extends BlockEntityType<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
		return TILE_ENTITIES.register(name, sup);
	}

}

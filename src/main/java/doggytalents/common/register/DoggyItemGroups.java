/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.Collections;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import doggytalents.common.block.DogBedBlock;
import doggytalents.common.block.DogBedMaterialManager;
import doggytalents.common.lib.Constants;
import doggytalents.common.util.DogBedUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_itemgroup.ump"
public class DoggyItemGroups {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	/**
	 * TODO using vanilla key, not forge's key ???
	 */
	public static final DeferredRegister<CreativeModeTab> ITEM_GROUP = DeferredRegister
			.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyItemGroups() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 48 "../../../register_itemgroup.ump"
	public static RegistryObject<CreativeModeTab> register(String name, Supplier<CreativeModeTab> sup) {
		return ITEM_GROUP.register(name, sup);
	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 9 "../../../register_itemgroup.ump"
	public static final RegistryObject<CreativeModeTab> GENERAL = register("tabgeneral",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup.doggytalents")).icon(() -> {
				return new ItemStack(DoggyItems.DOGGY_CHARM.get());
			}

// line 12 "../../../register_itemgroup.ump"
			).displayItems((a, b) -> {
				var allDTItemsIter = DoggyItems.ITEMS.getEntries();
				for (var val : allDTItemsIter) {
					if (val.get() instanceof BlockItem blockItem) {
						if (blockItem.getBlock() instanceof DogBedBlock) {
							continue;
						}
					}
					b.accept(val.get());
				}
			}
// line 22 "../../../register_itemgroup.ump"
			).build());

// line 24 "../../../register_itemgroup.ump"
	public static final RegistryObject<CreativeModeTab> DOG_BED = register("tabdogbed",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup.doggytalents.dogbed"))
					.icon(DogBedUtil::createRandomBed).withTabsBefore(GENERAL.getKey()).displayItems((a, b) -> {
						final int maxBeddingEntries = 13;
						final int maxCasingEntries = 13;
						var beddingList = DogBedMaterialManager.getBeddings().entrySet().stream().map(x -> x.getValue())
								.filter(x -> !(x instanceof DogBedMaterialManager.NaniBedding))
								.collect(Collectors.toList());
						var casingList = DogBedMaterialManager.getCasings().entrySet().stream().map(x -> x.getValue())
								.filter(x -> !(x instanceof DogBedMaterialManager.NaniCasing))
								.collect(Collectors.toList());

						Collections.shuffle(beddingList);
						Collections.shuffle(casingList);
						for (int i = 0; i < Math.min(maxCasingEntries, casingList.size()); ++i) {
							for (int j = 0; j < Math.min(maxBeddingEntries, beddingList.size()); ++j) {
								var beddingId = beddingList.get(j);
								var casingId = casingList.get(i);
								b.accept(DogBedUtil.createItemStack(casingId, beddingId));
							}
						}
					}
// line 45 "../../../register_itemgroup.ump"
					).build());

}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import doggytalents.common.entity.Dog;
import doggytalents.common.inventory.container.DogArmorContainer;
import doggytalents.common.inventory.container.DogInventoriesContainer;
import doggytalents.common.inventory.container.DoggyToolsMenu;
import doggytalents.common.inventory.container.FoodBowlContainer;
import doggytalents.common.inventory.container.PackPuppyContainer;
import doggytalents.common.inventory.container.RiceMillMenu;
import doggytalents.common.inventory.container.TreatBagContainer;
import doggytalents.common.lib.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_container.ump"
public class DoggyContainerTypes {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister
			.create(ForgeRegistries.Keys.MENU_TYPES, Constants.MOD_ID);

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyContainerTypes() {
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

	// line 6 "../../../register_container.ump"
	public static final RegistryObject<MenuType<FoodBowlContainer>> FOOD_BOWL = register("food_bowl",
			(windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				return new FoodBowlContainer(windowId, inv.player.level(), pos, inv, inv.player);
			}
// line 10 "../../../register_container.ump"
	);

// line 12 "../../../register_container.ump"
	public static final RegistryObject<MenuType<PackPuppyContainer>> PACK_PUPPY = register("pack_puppy",
			(windowId, inv, data) -> {
				Entity entity = inv.player.level().getEntity(data.readInt());
				return entity instanceof Dog ? new PackPuppyContainer(windowId, inv, (Dog) entity) : null;
			}
// line 16 "../../../register_container.ump"
	);

// line 18 "../../../register_container.ump"
	public static final RegistryObject<MenuType<TreatBagContainer>> TREAT_BAG = register("treat_bag",
			(windowId, inv, data) -> {
				int slotId = data.readByte();
				return new TreatBagContainer(windowId, inv, slotId, data.readItem());
			}
// line 22 "../../../register_container.ump"
	);

// line 24 "../../../register_container.ump"
	public static final RegistryObject<MenuType<DogInventoriesContainer>> DOG_INVENTORIES = register("dog_inventories",
			(windowId, inv, data) -> {
				int noDogs = data.readInt();
				List<Dog> dogs = new ArrayList<>(noDogs);
				for (int i = 0; i < noDogs; ++i) {
					Entity entity = inv.player.level().getEntity(data.readInt());
					if (entity instanceof Dog) {
						dogs.add((Dog) entity);
					}
				}
				return !dogs.isEmpty() ? new DogInventoriesContainer(windowId, inv, dogs) : null;
			}
// line 35 "../../../register_container.ump"
	);

// line 37 "../../../register_container.ump"
	public static final RegistryObject<MenuType<DogArmorContainer>> DOG_ARMOR = register("dog_armor",
			(windowId, inv, data) -> {
				int dogId = data.readInt();
				var e = inv.player.level().getEntity(dogId);
				if (!(e instanceof Dog))
					return null;
				return new DogArmorContainer(windowId, inv, (Dog) e);
			}
// line 44 "../../../register_container.ump"
	);

// line 46 "../../../register_container.ump"
	public static final RegistryObject<MenuType<DoggyToolsMenu>> DOG_TOOLS = register("dog_tools",
			(windowId, inv, data) -> {
				int dogId = data.readInt();
				var e = inv.player.level().getEntity(dogId);
				if (!(e instanceof Dog))
					return null;
				return new DoggyToolsMenu(windowId, inv, (Dog) e);
			}
// line 53 "../../../register_container.ump"
	);

// line 55 "../../../register_container.ump"
	public static final RegistryObject<MenuType<RiceMillMenu>> RICE_MILL = register("rice_mill",
			(windowId, inv, data) -> {
				var pos = data.readBlockPos();
				return new RiceMillMenu(windowId, inv, pos);
			}
// line 59 "../../../register_container.ump"
	);

// line 62 "../../../register_container.ump"
	private static <X extends AbstractContainerMenu, T extends MenuType<X>> RegistryObject<MenuType<X>> register(
			final String name, final IContainerFactory<X> factory) {
		return register(name, () -> IForgeMenuType.create(factory));
	}

// line 67 "../../../register_container.ump"
	private static <T extends MenuType<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
		return CONTAINERS.register(name, sup);
	}

}
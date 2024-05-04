/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.function.Supplier;

import doggytalents.api.registry.AccessoryType;
import doggytalents.common.lib.Constants;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_accessorytype.ump"
public class DoggyAccessoryTypes {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<AccessoryType> ACCESSORY_TYPES = DeferredRegister
			.create(DoggyRegistries.Keys.ACCESSORY_TYPE_REGISTRY, Constants.MOD_ID);
	public static final RegistryObject<AccessoryType> COLLAR = register("collar");
	public static final RegistryObject<AccessoryType> BOWTIE = register("bowtie");
	public static final RegistryObject<AccessoryType> CLOTHING = register("clothing");
	public static final RegistryObject<AccessoryType> GLASSES = register("glasses");
	public static final RegistryObject<AccessoryType> CONTACTS = register("contacts");
	public static final RegistryObject<AccessoryType> BAND = register("band");
	public static final RegistryObject<AccessoryType> HEAD = register("head");
	public static final RegistryObject<AccessoryType> FEET = register("feet");
	public static final RegistryObject<AccessoryType> TAIL = register("tail");
	public static final RegistryObject<AccessoryType> WINGS = register("wings");

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyAccessoryTypes() {
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

	// line 18 "../../../register_accessorytype.ump"
	private static RegistryObject<AccessoryType> register(final String name) {
		return register(name, () -> new AccessoryType());
	}

// line 22 "../../../register_accessorytype.ump"
	private static <T extends AccessoryType> RegistryObject<T> register(final String name, final Supplier<T> sup) {
		return ACCESSORY_TYPES.register(name, sup);
	}

}
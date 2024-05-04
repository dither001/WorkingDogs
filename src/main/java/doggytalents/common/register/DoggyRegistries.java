/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import doggytalents.api.DoggyTalentsAPI;
import doggytalents.api.registry.Accessory;
import doggytalents.api.registry.AccessoryType;
import doggytalents.api.registry.Talent;
import doggytalents.common.util.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

// line 21 "../../../register_main.ump"
public class DoggyRegistries {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyRegistries() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 25 "../../../register_main.ump"
	public static void newRegistry(NewRegistryEvent event) {
		DoggyTalentsAPI.TALENTS = event.create(makeRegistry(Keys.TALENTS_REGISTRY, Talent.class));
		DoggyTalentsAPI.ACCESSORIES = event.create(makeRegistry(Keys.ACCESSORIES_REGISTRY, Accessory.class));
		DoggyTalentsAPI.ACCESSORY_TYPE = event
				.create(makeRegistry(Keys.ACCESSORY_TYPE_REGISTRY, AccessoryType.class).disableSync());
	}
	/* PLEASE DO NOT EDIT THIS CODE */
	/*
	 * This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling
	 * language!
	 */

	// line 35 "../../../register_main.ump"
	public static class Keys {

		// ------------------------
		// MEMBER VARIABLES
		// ------------------------

		// ------------------------
		// CONSTRUCTOR
		// ------------------------

		public Keys() {
		}

		// ------------------------
		// INTERFACE
		// ------------------------

		public void delete() {
		}

		// ------------------------
		// DEVELOPER CODE - PROVIDED AS-IS
		// ------------------------

		// line 36 "../../../register_main.ump"
		public static final ResourceLocation TALENTS_REGISTRY = Util.getResource("talents");
		// line 37 "../../../register_main.ump"
		public static final ResourceLocation ACCESSORIES_REGISTRY = Util.getResource("accessories");
		// line 38 "../../../register_main.ump"
		public static final ResourceLocation ACCESSORY_TYPE_REGISTRY = Util.getResource("accessory_type");
		// line 39 "../../../register_main.ump"
		public static final ResourceLocation BEDDING_REGISTRY = Util.getResource("bedding");
		// line 40 "../../../register_main.ump"
		public static final ResourceLocation CASING_REGISTRY = Util.getResource("casing");

	}
	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 31 "../../../register_main.ump"
	private static <T> RegistryBuilder<T> makeRegistry(final ResourceLocation rl, Class<T> type) {
		return new RegistryBuilder<T>().setName(rl);
	}

}
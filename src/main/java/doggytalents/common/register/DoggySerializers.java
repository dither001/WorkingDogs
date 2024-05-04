/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.function.Supplier;

import doggytalents.common.entity.serializers.BedLocationsSerializer;
import doggytalents.common.entity.serializers.ClassicalVarSerializer;
import doggytalents.common.entity.serializers.CollarSerializer;
import doggytalents.common.entity.serializers.DogLevelSerializer;
import doggytalents.common.entity.serializers.DogSizeSerializer;
import doggytalents.common.entity.serializers.DogSkinDataSerializer;
import doggytalents.common.entity.serializers.DoggyArtifactsSerializer;
import doggytalents.common.entity.serializers.GenderSerializer;
import doggytalents.common.entity.serializers.IncapacitatedSyncSerializer;
import doggytalents.common.entity.serializers.ModeSerializer;
import doggytalents.common.lib.Constants;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_storage.ump"
public class DoggySerializers {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, Constants.MOD_ID);
	public static final RegistryObject<EntityDataSerializer> CLASSICAL_VAR = register2("classical_var",
			ClassicalVarSerializer::new);
	public static final RegistryObject<EntityDataSerializer> COLLAR_TYPE_SERIALIZER = register2("collar",
			CollarSerializer::new);
	public static final RegistryObject<EntityDataSerializer> GENDER_SERIALIZER = register2("gender",
			GenderSerializer::new);
	public static final RegistryObject<EntityDataSerializer> MODE_SERIALIZER = register2("mode", ModeSerializer::new);
	public static final RegistryObject<EntityDataSerializer> DOG_LEVEL_SERIALIZER = register2("dog_level",
			DogLevelSerializer::new);
	public static final RegistryObject<EntityDataSerializer> BED_LOC_SERIALIZER = register2("dog_bed_location",
			BedLocationsSerializer::new);
	public static final RegistryObject<EntityDataSerializer> INCAP_SYNC_SERIALIZER = register2("incap_sync",
			IncapacitatedSyncSerializer::new);
	public static final RegistryObject<EntityDataSerializer> ARTIFACTS_SERIALIZER = register2("doggy_artifacts",
			DoggyArtifactsSerializer::new);
	public static final RegistryObject<EntityDataSerializer> DOG_SIZE_SERIALIZER = register2("dog_size",
			DogSizeSerializer::new);
	public static final RegistryObject<EntityDataSerializer> DOG_SKIN_DATA_SERIALIZER = register2("dog_skin_data",
			DogSkinDataSerializer::new);

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggySerializers() {
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

	// line 18 "../../../register_storage.ump"
	private static <X extends EntityDataSerializer<?>> RegistryObject<EntityDataSerializer> register2(final String name,
			final Supplier<X> factory) {
		return register(name, () -> factory.get());
	}

// line 22 "../../../register_storage.ump"
	private static RegistryObject<EntityDataSerializer> register(final String name,
			final Supplier<EntityDataSerializer> sup) {
		return SERIALIZERS.register(name, sup);
	}

}
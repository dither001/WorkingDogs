/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.function.Supplier;

import doggytalents.common.effects.NattoBiteEffect;
import doggytalents.common.lib.Constants;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_effect.ump"
public class DoggyEffects {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.Keys.MOB_EFFECTS,
			Constants.MOD_ID);
	public static final RegistryObject<NattoBiteEffect> NATTO_BITE = register("natto_bite",
			() -> new NattoBiteEffect());

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyEffects() {
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

	// line 10 "../../../register_effect.ump"
	public static <T extends MobEffect> RegistryObject<T> register(String id, Supplier<T> sup) {
		return EFFECTS.register(id, sup);
	}

}
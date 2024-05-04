/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.function.Supplier;

import doggytalents.common.lib.Constants;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// line 2 "../../../register_attribute.ump"
public class DoggyAttributes {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister
			.create(ForgeRegistries.Keys.ATTRIBUTES, Constants.MOD_ID);
	public static final RegistryObject<Attribute> JUMP_POWER = register("generic.jump_power",
			() -> new RangedAttribute("attribute.name.generic.jump_power", 0.0D, 0.0D, 1.0D).setSyncable(true));
	public static final RegistryObject<Attribute> CRIT_CHANCE = register("generic.crit_chance",
			() -> new RangedAttribute("attribute.name.generic.crit_chance", 0.0D, 0.0D, 1.0D));
	public static final RegistryObject<Attribute> CRIT_BONUS = register("generic.crit_bonus",
			() -> new RangedAttribute("attribute.name.generic.crit_bonus", 0.0D, 0.0D, 1.0D));

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyAttributes() {
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

	// line 12 "../../../register_attribute.ump"
	private static <T extends Attribute> RegistryObject<T> register(final String name, final Supplier<T> sup) {
		return ATTRIBUTES.register(name, sup);
	}

}
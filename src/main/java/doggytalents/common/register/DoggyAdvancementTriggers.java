/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import java.util.HashMap;
import java.util.Map;

import doggytalents.common.advancement.DogDrunkTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceLocation;

// line 2 "../../../register_advancement.ump"
public class DoggyAdvancementTriggers {
	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	private static final Map<ResourceLocation, CriterionTrigger<?>> TRIGGERS = new HashMap<>();

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final DogDrunkTrigger DOG_DRUNK_TRIGGER = register(new DogDrunkTrigger());
//	public static final OokamikazeTrigger OOKAMIKAZE_TRIGGER = register(new OokamikazeTrigger());

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyAdvancementTriggers() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 20 "../../../register_advancement.ump"
	public static void registerAll() {
		for (var x : TRIGGERS.entrySet()) {
			CriteriaTriggers.register(x.getValue());
		}
	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 10 "../../../register_advancement.ump"
	public static <T extends CriterionTrigger<?>> T register(T p_10596_) {
		if (!TRIGGERS.containsKey(p_10596_.getId())) {
			TRIGGERS.put(p_10596_.getId(), p_10596_);
			return p_10596_;
		}

		return null;
	}

}
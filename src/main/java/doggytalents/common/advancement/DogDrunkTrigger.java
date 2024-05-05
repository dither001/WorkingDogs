/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.advancement;

import com.google.gson.JsonObject;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.common.util.Util;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

// line 2 "../../../drunk_dog.ump"
public class DogDrunkTrigger extends SimpleCriterionTrigger<DogDrunkTrigger.TriggerInstance> {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final ResourceLocation ID = Util.getResource("get_dog_drunk");

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DogDrunkTrigger() {
		super();
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	@Override
	// line 10 "../../../drunk_dog.ump"
	protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate player,
			DeserializationContext context) {
		return new TriggerInstance(player);
	}

	@Override
	// line 15 "../../../drunk_dog.ump"
	public ResourceLocation getId() {
		return ID;
	}

	// line 19 "../../../drunk_dog.ump"
	public void trigger(AbstractDog dog, ServerPlayer player) {
		this.trigger(player, x -> true);
	}

	/**
	 * 
	 */
	// line 24 "../../../drunk_dog.ump"
	public static TriggerInstance getInstance() {
		return new TriggerInstance(ContextAwarePredicate.ANY);
	}

	public String toString() {
		return super.toString() + "[" + "]";
	} /* PLEASE DO NOT EDIT THIS CODE */
	/*
	 * This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling
	 * language!
	 */

	// line 27 "../../../drunk_dog.ump"
	public static class TriggerInstance extends AbstractCriterionTriggerInstance {

		// ------------------------
		// INTERFACE
		// ------------------------

		public void delete() {
		}

		// line 31 "../../../drunk_dog.ump"
		public TriggerInstance(ContextAwarePredicate player) {
			super(ID, player);
		}

	}
}
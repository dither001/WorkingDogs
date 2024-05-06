/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.advancement;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import doggytalents.common.data.DisplayInfoBuilder;
import doggytalents.common.register.DoggyItems;
import doggytalents.common.util.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerInteractTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

// line 2 "../../../advancement_provider.ump"
public class DTAdvancementProvider extends ForgeAdvancementProvider {
	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 14 "../../../advancement_provider.ump"
	public DTAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries,
			ExistingFileHelper existingFileHelper) {
		/* custom constructor */
		super(output, registries, existingFileHelper, List.of(new DoggyAdvancementsSubProvider()));
	}

// line 23 "../../../advancement_provider.ump"
	public static class DoggyAdvancementsSubProvider implements ForgeAdvancementProvider.AdvancementGenerator {
		@Override
		public void generate(Provider registries, Consumer<Advancement> consumer,
				ExistingFileHelper existingFileHelper) {
			Advancement charm_advancement = Advancement.Builder.advancement()
					.display(DisplayInfoBuilder.create().icon(DoggyItems.DOGGY_CHARM).frame(FrameType.TASK)
							.translate("doggy_charm_summon").background("adventure.png").build())
					.addCriterion("summon_dog",
							ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
									LocationPredicate.Builder.location(),
									ItemPredicate.Builder.item().of(DoggyItems.DOGGY_CHARM.get())))
					.save(consumer, Util.getResourcePath("dtn_core/summon_dog"));

			Advancement train_dog_advancement = Advancement.Builder.advancement().parent(charm_advancement)
					.display(DisplayInfoBuilder.create().icon(DoggyItems.TRAINING_TREAT).frame(FrameType.TASK)
							.translate("train_dog_hajimemashite").build())
					.addCriterion("train_dog",
							PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
									ItemPredicate.Builder.item().of(DoggyItems.TRAINING_TREAT.get()),
									EntityPredicate.wrap(EntityPredicate.Builder.entity().of(EntityType.WOLF).build())))
					.save(consumer, Util.getResourcePath("dtn_core/train_dog"));

			Advancement sake_advancement = Advancement.Builder.advancement()
					.display(DisplayInfoBuilder.create().icon(DoggyItems.SAKE).frame(FrameType.TASK)
							.translate("get_dog_drunk").build())
					.addCriterion("get_dog_drunk", DogDrunkTrigger.getInstance())
					.save(consumer, Util.getResourcePath("default/get_dog_drunk"));

//			Advancement ookamikaze_advancement = Advancement.Builder.advancement()
//					.display(DisplayInfoBuilder.create().icon(() -> Items.GUNPOWDER).frame(FrameType.TASK)
//							.translate("ookamikaze_trigger").build())
//					.addCriterion("ookamikaze_trigger", OokamikazeTrigger.getInstance())
//					.save(consumer, Util.getResourcePath("default/ookamikaze_trigger"));
		}
	}

}
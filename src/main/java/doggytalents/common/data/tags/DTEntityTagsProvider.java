/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.data.tags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import doggytalents.common.lib.Constants;
import doggytalents.common.register.DoggyTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

// line 23 "../../../../data_tags.ump"
public class DTEntityTagsProvider extends EntityTypeTagsProvider {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DTEntityTagsProvider(PackOutput aOutput, CompletableFuture<Provider> aProvider, String aModId,
			ExistingFileHelper aHelper) {
		super(aOutput, aProvider, aModId, aHelper);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 30 "../../../../data_tags.ump"
	public DTEntityTagsProvider(PackOutput output, CompletableFuture<Provider> provider,
			ExistingFileHelper fileHelper) {
		super(output, provider, Constants.MOD_ID, fileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	// line 36 "../../../../data_tags.ump"
	public String getName() {
		return "DoggyTalents Entity Tags";
	}

	@Override
	// line 41 "../../../../data_tags.ump"
	public void addTags(Provider provider) {
		createTag(DoggyTags.DOG_SHOULD_IGNORE, () -> EntityType.ENDERMAN);
		createTag(DoggyTags.DROP_SOY_WHEN_DOG_KILL, () -> EntityType.CREEPER, () -> EntityType.ZOMBIE,
				() -> EntityType.SKELETON, () -> EntityType.SPIDER);
		createTag(DoggyTags.MOB_RETRIEVER_MUST_IGNORE, () -> EntityType.CREEPER);
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 47 "../../../../data_tags.ump"
	private final @SafeVarargs void createTag(TagKey<EntityType<?>> tag,
			Supplier<? extends EntityType<?>>... entities) {
		tag(tag).add(Arrays.stream(entities).map(Supplier::get).toArray(EntityType<?>[]::new));
	}

}
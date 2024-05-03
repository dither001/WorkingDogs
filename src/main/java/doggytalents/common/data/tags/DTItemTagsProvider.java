/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.data.tags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import doggytalents.common.lib.Constants;
import doggytalents.common.register.DoggyItems;
import doggytalents.common.register.DoggyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

// line 2 "../../../../data_tags.ump"
public class DTItemTagsProvider extends ItemTagsProvider {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 12 "../../../../data_tags.ump"
	public DTItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> holderLookup,
			CompletableFuture<TagsProvider.TagLookup<Block>> blockTagLookup, ExistingFileHelper fileHelper) {
		/* custom constructor */
		super(packOutput, holderLookup, blockTagLookup, Constants.MOD_ID, fileHelper);
	}

// line 19 "../../../../data_tags.ump"
	public void addTags(HolderLookup.Provider provider) {
		createTag(DoggyTags.BEG_ITEMS_TAMED, DoggyItems.THROW_STICK, DoggyItems.THROW_BONE, () -> Items.BONE,
				DoggyItems.FRISBEE);
		appendToTag(DoggyTags.TREATS);
		createTag(DoggyTags.BEG_ITEMS_UNTAMED, DoggyItems.TRAINING_TREAT, () -> Items.BONE);
		createTag(DoggyTags.BREEDING_ITEMS, DoggyItems.BREEDING_BONE);
		createTag(DoggyTags.PACK_PUPPY_BLACKLIST, DoggyItems.THROW_BONE, DoggyItems.THROW_BONE_WET,
				DoggyItems.THROW_STICK, DoggyItems.THROW_STICK_WET);
		createTag(DoggyTags.TREATS, DoggyItems.TRAINING_TREAT, DoggyItems.SUPER_TREAT, DoggyItems.MASTER_TREAT,
				DoggyItems.KAMI_TREAT);
		tag(ItemTags.MUSIC_DISCS).add(DoggyItems.MUSIC_DISC_BWV_1080_FUGUE_11_KIMIKO.get(),
				DoggyItems.MUSIC_DISC_BWV_849_FUGUE_KIMIKO.get(), DoggyItems.MUSIC_DISC_OKAMI_1.get(),
				DoggyItems.MUSIC_DISC_CHOPIN_OP64_NO1.get());
	}

// line 35 "../../../../data_tags.ump"
	private @SafeVarargs final void appendToTag(TagKey<Item> tag, TagKey<Item>... toAppend) {
		tag(tag).addTags(toAppend);
	}

// line 40 "../../../../data_tags.ump"
	private @SafeVarargs final void createTag(TagKey<Item> tag, Supplier<? extends ItemLike>... items) {
		tag(tag).add(Arrays.stream(items).map(Supplier::get).map(ItemLike::asItem).toArray(Item[]::new));
	}

}
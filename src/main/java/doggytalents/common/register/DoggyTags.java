/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.register;

import doggytalents.common.util.Util;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

// line 2 "../../../register_tag.ump"
public class DoggyTags {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	public static final TagKey<Item> BEG_ITEMS_TAMED = tag("beg_items_tamed");
	public static final TagKey<Item> BEG_ITEMS_UNTAMED = tag("beg_items_untamed");
	public static final TagKey<Item> BREEDING_ITEMS = tag("breeding_items");
	public static final TagKey<Item> PACK_PUPPY_BLACKLIST = tag("pack_puppy_blacklist");
	public static final TagKey<Item> TREATS = tag("treats");
	public static final TagKey<Item> WHITELIST_FOOD = tag("whitelist_food");
	public static final TagKey<EntityType<?>> DOG_SHOULD_IGNORE = tagEntity("dog_should_ignore");
	public static final TagKey<EntityType<?>> DROP_SOY_WHEN_DOG_KILL = tagEntity("drop_soy_when_dog_kill");
	public static final TagKey<EntityType<?>> MOB_RETRIEVER_MUST_IGNORE = tagEntity("mob_retriever_must_ignore");

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyTags() {
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 16 "../../../register_tag.ump"
	private static TagKey<Item> tag(String name) {
		return ItemTags.create(Util.getResource(name));
	}

	// line 20 "../../../register_tag.ump"
	private static TagKey<EntityType<?>> tagEntity(String name) {
		return ForgeRegistries.ENTITY_TYPES.tags().createTagKey(Util.getResource(name));
	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
}
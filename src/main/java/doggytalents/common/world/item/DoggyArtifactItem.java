/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.world.item;

import java.util.function.Supplier;

import doggytalents.common.artifacts.DoggyArtifact;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

// line 27 "../../../../world_item.ump"
public class DoggyArtifactItem extends Item {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// DoggyArtifactItem Attributes
	private Supplier<DoggyArtifact> supplier;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public DoggyArtifactItem(Properties aProperties, Supplier<DoggyArtifact> aSupplier) {
		super(aProperties);
		supplier = aSupplier;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
	}

	// line 35 "../../../../world_item.ump"
	public DoggyArtifact createArtifact() {
		return this.supplier.get();
	}

	// line 39 "../../../../world_item.ump"
	public static CompoundTag writeCompound(DoggyArtifactItem item) {
		var id = ForgeRegistries.ITEMS.getKey(item);
		if (id == null)
			return null;
		var artifactTag = new CompoundTag();
		artifactTag.putString("type", id.toString());
		return artifactTag;
	}

	// line 48 "../../../../world_item.ump"
	public static DoggyArtifactItem readCompound(CompoundTag tag) {
		var id_str = tag.getString("type");
		var item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id_str));
		if (item == null)
			return null;
		if (!(item instanceof DoggyArtifactItem artifactItem))
			return null;
		return artifactItem;
	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package doggytalents.common.data.tags;

import java.util.concurrent.CompletableFuture;

import doggytalents.common.lib.Constants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

// line 2 "../../../../data_tags.ump"
public class DTBlockTagsProvider extends BlockTagsProvider {

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

	// line 13 "../../../../data_tags.ump"
	public String getName() {
		return "DoggyTalents Block Tags";
	}

	@Override
	// line 18 "../../../../data_tags.ump"
	protected void addTags(Provider p_256380_) {
		// TODO Auto-generated method stub
	}

	// ------------------------
	// DEVELOPER CODE - PROVIDED AS-IS
	// ------------------------

	// line 6 "../../../../data_tags.ump"
	public DTBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
			ExistingFileHelper existingFileHelper) {
		/* custom constructor */
		super(output, lookupProvider, Constants.MOD_ID, existingFileHelper);
	}

}
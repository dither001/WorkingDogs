package doggytalents.client.entity.render.layer.accessory.modelrenderentry;

import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.model.BowTieModel;
import doggytalents.client.entity.model.SyncedAccessoryModel;
import doggytalents.client.entity.render.AccessoryModelManager;
import doggytalents.common.lib.Constants;
import doggytalents.common.lib.Resources;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

public class BowtieRenderEntry extends AccessoryModelManager.Entry {

    public static final ModelLayerLocation DOG_BOWTIE = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "dog_bowtie"), "main");

    private BowTieModel model;

    @Override
    public void initModel(Context ctx) {
        this.model = new BowTieModel(ctx.bakeLayer(DOG_BOWTIE));
    }

    @Override
    public SyncedAccessoryModel getModel() {
        return this.model;
    }

    @Override
    public void registerLayerDef(RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DOG_BOWTIE, BowTieModel::createBowtieLayer);
    }

    @Override
    public ResourceLocation getResources(AccessoryInstance inst) {
        return Resources.DYABLE_BOW_TIE;
    }

    @Override
    public boolean isDyable() {
        return true;
    }
    
}

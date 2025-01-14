package doggytalents.client.entity.render.layer.accessory.modelrenderentry;

import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.model.BeastarsUniformFemaleAugmentModel;
import doggytalents.client.entity.model.SyncedAccessoryModel;
import doggytalents.client.entity.render.AccessoryModelManager;
import doggytalents.common.lib.Constants;
import doggytalents.common.lib.Resources;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

public class BeastarsUniformFemaleEntry extends AccessoryModelManager.Entry {
    
    public static final ModelLayerLocation BEASTARS_UNIFORM_F_AUGMENT = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "beastars_uniform_f_augment"), "main");

    private BeastarsUniformFemaleAugmentModel model;

    @Override
    public void initModel(Context ctx) {
        this.model = new BeastarsUniformFemaleAugmentModel(ctx.bakeLayer(BEASTARS_UNIFORM_F_AUGMENT));
    }

    @Override
    public SyncedAccessoryModel getModel() {
        return this.model;
    }

    @Override
    public void registerLayerDef(RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BEASTARS_UNIFORM_F_AUGMENT, BeastarsUniformFemaleAugmentModel::createLayer);
    }

    @Override
    public ResourceLocation getResources(AccessoryInstance inst) {
        return Resources.BEASTARS_UNIFORM_FEMALE;
    }


}

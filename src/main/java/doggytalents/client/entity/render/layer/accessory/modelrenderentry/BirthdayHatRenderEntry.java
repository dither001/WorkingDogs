package doggytalents.client.entity.render.layer.accessory.modelrenderentry;

import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.model.BirthdayHatModel;
import doggytalents.client.entity.model.SyncedAccessoryModel;
import doggytalents.common.lib.Constants;
import doggytalents.common.lib.Resources;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

public class BirthdayHatRenderEntry extends DoubleDyableRenderEntry {
    public static final ModelLayerLocation DOG_BIRTHDAY_HAT = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "dog_birthday_hat"), "main");
    
    public BirthdayHatModel model;
    @Override
    public void initModel(Context ctx) {
        this.model = new BirthdayHatModel(ctx.bakeLayer(DOG_BIRTHDAY_HAT));
    }

    @Override
    public SyncedAccessoryModel getModel() {
        return this.model;
    }

    @Override
    public void registerLayerDef(RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DOG_BIRTHDAY_HAT, BirthdayHatModel::createBodyLayer);
    }

    @Override
    protected ResourceLocation getFgResource(AccessoryInstance inst) {
        return Resources.BIRTHDAY_HAT_FG;
    }

    @Override
    protected ResourceLocation getBgResource(AccessoryInstance inst) {
        return Resources.BIRTHDAY_HAT_BG;
    }
    
}

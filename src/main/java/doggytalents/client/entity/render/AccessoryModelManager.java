package doggytalents.client.entity.render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import doggytalents.api.inferface.IColoredObject;
import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.model.SyncedAccessoryModel;
import doggytalents.client.entity.model.dog.DogModel;
import doggytalents.client.entity.render.layer.accessory.DefaultAccessoryRenderer;
import doggytalents.common.entity.Dog;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class AccessoryModelManager {

    private static ArrayList<Entry> entryArray = new ArrayList<Entry>();
    private static List<Entry> entryArraySyncedView = Collections.synchronizedList(entryArray);

    public static void register(Entry entry) {
        if (entryArraySyncedView.contains(entry)) return;
        entryArraySyncedView.add(entry);
    }

    public static void resolve(EntityRendererProvider.Context ctx) {
        for (var x : entryArraySyncedView) {
            x.initModel(ctx);
        }
    }

    public static void registerLayerDef(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (var x : entryArraySyncedView) {
            x.registerLayerDef(event);
        }
    }

    public static abstract class Entry {
        
        public abstract void initModel(EntityRendererProvider.Context ctx);
        public abstract SyncedAccessoryModel getModel();
        public void renderAccessory(RenderLayer<Dog, DogModel> layer, 
            PoseStack poseStack, MultiBufferSource buffer, int packedLight, 
            Dog dog, float limbSwing, float limbSwingAmount, float partialTicks, 
            float ageInTicks, float netHeadYaw, float headPitch, AccessoryInstance inst) {
            
            var model = this.getModel();
            var dogModel = layer.getParentModel();
            dogModel.copyPropertiesTo(model);
            model.prepareMobModel(dog, limbSwing, limbSwingAmount, partialTicks);
            model.setupAnim(dog, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            model.sync(dogModel);
            
            float[] color = new float[]{1.0f, 1.0f, 1.0f};
            if (this.isDyable() && (inst instanceof IColoredObject coloredObject))
                color = coloredObject.getColor();
            
            if (isTranslucent()) {
                DefaultAccessoryRenderer.renderTranslucentModel(model, getResources(inst), 
                    poseStack, buffer, packedLight, dog, color[0], color[1], color[2], 1f);
            } else
            AccessoryModelManager.renderColoredCutoutModel(model, getResources(inst), 
                poseStack, buffer, packedLight, dog, color[0], color[1], color[2]);
        };
        public abstract void registerLayerDef(final EntityRenderersEvent.RegisterLayerDefinitions event);
        public abstract ResourceLocation getResources(AccessoryInstance inst);
        public boolean isDyable() { return false; }
        public boolean isTranslucent() { return false; }
    }

    public static void renderColoredCutoutModel(SyncedAccessoryModel p_117377_, ResourceLocation p_117378_, PoseStack p_117379_, MultiBufferSource p_117380_, int p_117381_, Dog p_117382_, float p_117383_, float p_117384_, float p_117385_) {
        VertexConsumer vertexconsumer = p_117380_.getBuffer(RenderType.entityCutoutNoCull(p_117378_));
        p_117377_.renderToBuffer(p_117379_, vertexconsumer, p_117381_, LivingEntityRenderer.getOverlayCoords(p_117382_, 0.0F), p_117383_, p_117384_, p_117385_, 1.0F);
    }

}

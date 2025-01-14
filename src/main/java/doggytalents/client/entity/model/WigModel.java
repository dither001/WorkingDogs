package doggytalents.client.entity.model;

import java.util.Optional;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class WigModel extends SyncedAccessoryModel {
	
	public WigModel(ModelPart root) {
		super(root);
	}

	@Override
	protected void populatePart(ModelPart box) {
		this.head = Optional.of(box.getChild("phead"));
		this.realHead = Optional.of(head.get().getChild("pwig"));
	}

	public static LayerDefinition createWigLayerDefinition() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("phead", CubeListBuilder.create(), PartPose.offset(0F, 13.5F, -7.0F));

        var pwig = head.addOrReplaceChild("pwig", CubeListBuilder.create(), PartPose.ZERO);

        pwig.addOrReplaceChild("wi", CubeListBuilder.create().texOffs(0, 0).addBox(2.75F, -12.25F, -9.35F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(2.75F, -8.75F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(3.0F, -11.3F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(3.0F, -9.3F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(3.0F, -10.3F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-4.0F, -12.25F, -9.35F, 2.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-4.5F, -11.3F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-4.5F, -9.3F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-3.75F, -8.75F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-2.75F, -9.3F, -9.1F, 0.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-4.85F, -10.3F, -9.1F, 1.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-3.5F, -13.0F, -9.6F, 7.0F, -1.0F, 4.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-2.25F, -14.5F, -9.1F, 5.0F, -1.0F, 3.0F, CubeDeformation.NONE)
		.texOffs(0, 0).addBox(-3.0F, -12.75F, -9.35F, 6.0F, -2.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(-0.1F, 11.05F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}

package doggytalents.client.entity.model.dog;

import doggytalents.common.entity.Dog;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class DeathModel extends DogModel<Dog> {
    
    public DeathModel(ModelPart box) {
        super(box);
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1.0F, 13.5F, -7.0F) );

		var real_head = head.addOrReplaceChild("real_head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(13, 7).addBox(-1.5F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(13, 7).addBox(2.5F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-0.5F, -0.02F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(12, 4).addBox(4.0F, -0.75F, -2.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 6).addBox(4.0F, -0.75F, -1.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(13, 5).addBox(-3.0F, -0.75F, -2.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 5).addBox(-4.0F, -0.75F, -1.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 6).addBox(-4.75F, -1.75F, -1.75F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 7).addBox(-3.25F, -2.5F, -1.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 7).addBox(2.25F, -2.5F, -1.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 6).addBox(-1.5F, -3.5F, -1.5F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 8).addBox(-1.5F, 2.5F, -1.5F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(9, 6).addBox(3.5F, -1.75F, -1.75F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		var ear_normal = real_head.addOrReplaceChild("ear_normal", CubeListBuilder.create(),PartPose.ZERO);
        var ear_boni = real_head.addOrReplaceChild("ear_boni", CubeListBuilder.create(),PartPose.ZERO);
        var ear_small = real_head.addOrReplaceChild("ear_small", CubeListBuilder.create(),PartPose.ZERO);

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 14).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        var upper_body = partdefinition.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(21, 0).addBox(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(-1.0F, 14.0F, -3.0F, 1.5707964F, 0.0F, 0.0F));

		upper_body.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(44, 24).addBox(-4.75F, -7.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-5.0F, -6.75F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(2.75F, -7.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(3.0F, -6.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(2.75F, -6.25F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-5.0F, -6.25F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(2.75F, -6.0F, 2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-5.0F, -6.0F, 2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(2.75F, -6.25F, 4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-5.0F, -6.25F, 4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-3.5F, -7.75F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(1.5F, -7.75F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(1.25F, -6.5F, -2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-2.25F, -7.25F, -2.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).mirror().addBox(0.25F, -7.25F, -2.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)).mirror(false)
		.texOffs(44, 24).addBox(-3.25F, -6.5F, -2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-3.25F, -6.5F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.15F))
		.texOffs(44, 24).addBox(-1.75F, -6.25F, 5.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(-1.5F, -7.5F, 4.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).addBox(-0.5F, -7.5F, 4.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).addBox(-0.25F, -6.25F, 5.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
		.texOffs(44, 24).addBox(1.25F, -6.5F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.15F))
		.texOffs(44, 24).addBox(2.0F, -6.0F, 4.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).addBox(-4.25F, -6.0F, 4.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.4F))
		.texOffs(44, 24).addBox(2.1F, -6.9F, 4.85F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).addBox(-4.4F, -6.9F, 4.85F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).addBox(3.25F, -7.25F, 3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).addBox(3.25F, -7.0F, 2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).addBox(3.25F, -6.95F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).mirror().addBox(-5.5F, -6.75F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false)
		.texOffs(44, 24).mirror().addBox(-5.3F, -6.75F, 0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false)
		.texOffs(44, 24).addBox(3.45F, -6.95F, 0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).mirror().addBox(-5.5F, -7.25F, 3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false)
		.texOffs(44, 24).mirror().addBox(-4.75F, -7.25F, 4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false)
		.texOffs(44, 24).addBox(2.75F, -7.25F, 4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(44, 24).mirror().addBox(-5.5F, -7.0F, 2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offset(1F, 2.75F, -1.25F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(-2.5F, 16.0F, 7.0F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(0.5F, 16.0F, 7.0F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(-2.5F, 16.0F, -4.0F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, CubeDeformation.NONE), PartPose.offset(0.5F, 16.0F, -4.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 13.5F, 8.5f, 1.8326F, 0.0F, 0.0F));

        PartDefinition real_tail_1 = tail.addOrReplaceChild("real_tail", CubeListBuilder.create().texOffs(9, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, CubeDeformation.NONE)
		.texOffs(56, 0).addBox(0.0F, 2.0F, 1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.25F))
		.texOffs(56, 0).addBox(0.0F, 5.0F, 2.0F, 2.0F, 3.0F, 2.0F, CubeDeformation.NONE)
		.texOffs(56, 0).addBox(0.0F, 4.0F, 4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.ZERO);
        
        tail.addOrReplaceChild("real_tail_2", CubeListBuilder.create(), PartPose.ZERO);
        
        tail.addOrReplaceChild("real_tail_bushy", CubeListBuilder.create(), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
    
    @Override
    public void prepareMobModel(Dog dog, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.tail.yRot = dog.getWagAngle(limbSwing, limbSwingAmount, partialTickTime);

        if (dog.isInSittingPose()) {
            if (dog.isLying()) {
                this.head.setPos(-1, 19.5F, -7);
                this.body.setPos(0, 20, 2);
                this.body.xRot = (float)Math.PI / 2F;
                this.mane.setPos(
                    -1f + MANE_LYING_OFF[0],
                    14f + MANE_LYING_OFF[1], 
                    -3f + MANE_LYING_OFF[2]
                );
                this.mane.xRot = this.body.xRot;
                this.tail.setPos(-1, 18, 8);
                this.legBackRight.setPos(-4.5F, 23, 7);
                this.legBackRight.xRot = -(float)Math.PI / 2F;
                this.legBackLeft.setPos(2.5F, 23, 7);
                this.legBackLeft.xRot = -(float)Math.PI / 2F;
                this.legFrontRight.setPos(-4.5F, 23, -4);
                this.legFrontRight.xRot = -(float)Math.PI / 2F;
                this.legFrontLeft.setPos(2.5F, 23, -4);
                this.legFrontLeft.xRot = -(float)Math.PI / 2F;
            }  else {
                this.head.setPos(-1.0F, 13.5F, -7.0F);
                this.mane.setPos(
                    -1f + MANE_SITTING_OFF[0],
                    14f + MANE_SITTING_OFF[1], 
                    -3f + MANE_SITTING_OFF[2]
                );
                this.mane.xRot = ((float)Math.PI * 2F / 5F);
                this.mane.yRot = 0.0F;
                this.body.setPos(0.0F, 18.0F, 0.0F);
                this.body.xRot = ((float)Math.PI / 4F);
                this.tail.setPos(-1F, 21.0F, 6.0F);
                this.legBackRight.setPos(-2.5F, 22.0F, 2.0F);
                this.legBackRight.xRot = ((float)Math.PI * 3F / 2F);
                this.legBackLeft.setPos(0.5F, 22.0F, 2.0F);
                this.legBackLeft.xRot = ((float)Math.PI * 3F / 2F);
                this.legFrontRight.xRot = 5.811947F;
                this.legFrontRight.setPos(-2.49F, 17.0F, -4.0F);
                this.legFrontLeft.xRot = 5.811947F;
                this.legFrontLeft.setPos(0.51F, 17.0F, -4.0F);


                this.head.setPos(-1.0F, 13.5F, -7.0F);
                this.legFrontRight.yRot = 0;
                this.legFrontLeft.yRot = 0;
            }
        } else {
            this.body.setPos(0.0F, 14.0F, 2.0F);
            this.body.xRot = ((float)Math.PI / 2F);
            this.mane.setPos(-1.0F, 14.0F, -3.0F);
            this.mane.xRot = this.body.xRot;
            this.tail.setPos(-1.0F, 12.0F, 8.0F);
            this.legBackRight.setPos(-2.5F, 16.0F, 7.0F);
            this.legBackLeft.setPos(0.5F, 16.0F, 7.0F);
            this.legFrontRight.setPos(-2.5F, 16.0F, -4.0F);
            this.legFrontLeft.setPos(0.5F, 16.0F, -4.0F);
            this.legBackRight.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legBackLeft.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontRight.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontLeft.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

            this.head.setPos(-1.0F, 13.5F, -7.0F);
            this.legFrontRight.yRot = 0.0F;
            this.legFrontLeft.yRot = 0.0F;
        }

        this.realHead.zRot = dog.getInterestedAngle(partialTickTime) + dog.getShakeAngle(partialTickTime, 0.0F);
        this.mane.zRot = dog.getShakeAngle(partialTickTime, -0.08F);
        this.body.zRot = dog.getShakeAngle(partialTickTime, -0.16F);
        this.realTail.zRot = dog.getShakeAngle(partialTickTime, -0.2F);
        this.realTail2.zRot = dog.getShakeAngle(partialTickTime, -0.2F);
        this.realTail3.zRot = dog.getShakeAngle(partialTickTime, -0.2F);

    }

}
package doggytalents.client.entity.model.animation.biganim;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ProtestAnimation {
    
    public static AnimationDefinition build() {
    var animDef = AnimationDefinition.Builder.withLength(6f);
        build1(animDef);
        build2(animDef);
        build3(animDef);

        return animDef.build();
    }

    public static void build1(AnimationDefinition.Builder builder) {
        builder.addAnimation("right_ear",
            new AnimationChannel(AnimationChannel.Targets.POSITION, 
                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(1.25f, KeyframeAnimations.posVec(0f, -0.25f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(5.734333f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("right_ear",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(0.7f, KeyframeAnimations.degreeVec(22.5f, 32.5f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.25f, KeyframeAnimations.degreeVec(-32.5f, 32.5f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.75f, KeyframeAnimations.degreeVec(41.82f, 18.99f, -7.26f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.95f, KeyframeAnimations.degreeVec(45.71f, 31.15f, -19.14f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(2.8676667f, KeyframeAnimations.degreeVec(45.71f, 31.15f, -19.14f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(3.0167666f, KeyframeAnimations.degreeVec(54.55f, 48.24f, 13.31f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(3.2f, KeyframeAnimations.degreeVec(45.71f, 31.15f, -19.14f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5f, KeyframeAnimations.degreeVec(45.71f, 31.15f, -19.14f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5.734333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("left_ear",
            new AnimationChannel(AnimationChannel.Targets.POSITION, 
                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(1.25f, KeyframeAnimations.posVec(0f, -0.25f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(5.734333f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("left_ear",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(0.38343336f, KeyframeAnimations.degreeVec(22.5f, 32.5f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.25f, KeyframeAnimations.degreeVec(-32.5f, 32.5f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.75f, KeyframeAnimations.degreeVec(41.82f, 18.99f, -7.26f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.95f, KeyframeAnimations.degreeVec(-8.07f, 4.05f, 8.42f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(3.7f, KeyframeAnimations.degreeVec(-8.07f, 4.05f, 8.42f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(3.8343335f, KeyframeAnimations.degreeVec(-10.6f, -40.42f, 15.91f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(3.95f, KeyframeAnimations.degreeVec(-8.07f, 4.05f, 8.42f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(4.983433f, KeyframeAnimations.degreeVec(-8.07f, 4.05f, 8.42f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5.283433f, KeyframeAnimations.degreeVec(-8.07f, 4.05f, 8.42f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5.4f, KeyframeAnimations.degreeVec(-4.77f, -43.15f, 6.99f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5.734333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("head",
            new AnimationChannel(AnimationChannel.Targets.POSITION, 
                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(2f, KeyframeAnimations.posVec(-0.25f, -0.5f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(5.5f, KeyframeAnimations.posVec(-0.25f, -0.5f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("head",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(0.5f, KeyframeAnimations.degreeVec(17.19f, 51.86f, -5.62f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.5f, KeyframeAnimations.degreeVec(-32.29f, 25.2f, 34.96f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.6343333f, KeyframeAnimations.degreeVec(-29.1f, 1.61f, -11.64f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.75f, KeyframeAnimations.degreeVec(8.4f, 1.61f, -11.64f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(2f, KeyframeAnimations.degreeVec(-2.35f, 11.23f, -13.49f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5.5f, KeyframeAnimations.degreeVec(-2.35f, 11.23f, -13.49f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(6f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("body",
            new AnimationChannel(AnimationChannel.Targets.POSITION, 
                new Keyframe(0f, KeyframeAnimations.posVec(0f, -4f, -2f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(1.5834333f, KeyframeAnimations.posVec(0f, -3.5f, -2f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(1.75f, KeyframeAnimations.posVec(0f, -4f, -2f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(2f, KeyframeAnimations.posVec(0f, -4.02f, -2f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(5.5f, KeyframeAnimations.posVec(0f, -4.02f, -2f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(6f, KeyframeAnimations.posVec(0f, -4f, -2f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("body",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(-39.5f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(-44.5f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(1.75f, KeyframeAnimations.degreeVec(-39.5f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(2f, KeyframeAnimations.degreeVec(-39.28f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5.5f, KeyframeAnimations.degreeVec(-39.28f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(6f, KeyframeAnimations.degreeVec(-39.5f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("right_hind_leg",
            new AnimationChannel(AnimationChannel.Targets.POSITION, 
                new Keyframe(0f, KeyframeAnimations.posVec(0f, -6f, -5f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(2f, KeyframeAnimations.posVec(0f, -6f, -5f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(5.5f, KeyframeAnimations.posVec(0f, -6f, -5f),
                    AnimationChannel.Interpolations.CATMULLROM), 
                new Keyframe(6f, KeyframeAnimations.posVec(0f, -6f, -5f),
                    AnimationChannel.Interpolations.CATMULLROM)))
        .addAnimation("right_hind_leg",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(2f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(5.5f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM),
                new Keyframe(6f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                    AnimationChannel.Interpolations.CATMULLROM)));
    }

    public static void build2(AnimationDefinition.Builder builder) {
        builder.addAnimation("left_hind_leg",
        new AnimationChannel(AnimationChannel.Targets.POSITION, 
            new Keyframe(0f, KeyframeAnimations.posVec(0f, -6f, -5f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(2f, KeyframeAnimations.posVec(0f, -6f, -5f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, -6f, -5f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(6f, KeyframeAnimations.posVec(0f, -6f, -5f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("left_hind_leg",
        new AnimationChannel(AnimationChannel.Targets.ROTATION,
            new Keyframe(0f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.5f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("right_front_leg",
        new AnimationChannel(AnimationChannel.Targets.POSITION, 
            new Keyframe(0f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(2f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(6f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("right_front_leg",
        new AnimationChannel(AnimationChannel.Targets.ROTATION,
            new Keyframe(0f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.5f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("left_front_leg",
        new AnimationChannel(AnimationChannel.Targets.POSITION, 
            new Keyframe(0f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(2f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(6f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("left_front_leg",
        new AnimationChannel(AnimationChannel.Targets.ROTATION,
            new Keyframe(0f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.5f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6f, KeyframeAnimations.degreeVec(-27f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("tail",
        new AnimationChannel(AnimationChannel.Targets.POSITION, 
            new Keyframe(0f, KeyframeAnimations.posVec(0f, -9f, -2f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(2f, KeyframeAnimations.posVec(0f, -9f, -2f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, -9f, -2f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(6f, KeyframeAnimations.posVec(0f, -9f, -2f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("tail",
        new AnimationChannel(AnimationChannel.Targets.ROTATION,
            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("upper_body",
        new AnimationChannel(AnimationChannel.Targets.POSITION, 
            new Keyframe(0f, KeyframeAnimations.posVec(0f, -2.25f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(1.5834333f, KeyframeAnimations.posVec(0f, -1.75f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(1.7834334f, KeyframeAnimations.posVec(0f, -2.25f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(2f, KeyframeAnimations.posVec(0f, -2.27f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, -2.27f, 0f),
                AnimationChannel.Interpolations.CATMULLROM), 
            new Keyframe(6f, KeyframeAnimations.posVec(0f, -2.25f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)))
    .addAnimation("upper_body",
        new AnimationChannel(AnimationChannel.Targets.ROTATION,
            new Keyframe(0f, KeyframeAnimations.degreeVec(-18f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(-30.5f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7834334f, KeyframeAnimations.degreeVec(-18f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2f, KeyframeAnimations.degreeVec(-17.5f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.5f, KeyframeAnimations.degreeVec(-17.5f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6f, KeyframeAnimations.degreeVec(-18f, 0f, 0f),
                AnimationChannel.Interpolations.CATMULLROM)));
    }

    public static void build3(AnimationDefinition.Builder builder) {
        
    }

}
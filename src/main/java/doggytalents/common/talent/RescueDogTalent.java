package doggytalents.common.talent;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import doggytalents.api.feature.DataKey;
import doggytalents.api.feature.EnumMode;
import doggytalents.api.inferface.AbstractDog;
import doggytalents.api.registry.Talent;
import doggytalents.api.registry.TalentInstance;
import doggytalents.common.entity.Dog;
import doggytalents.common.util.DogUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;

public class RescueDogTalent extends TalentInstance {

    private List<LivingEntity> healTargets = new ArrayList<LivingEntity>();
    private final int SEARCH_RADIUS = 12; //const ?? maybe can improve thru talents
    private int searchCoolDown;
    private int nextSearchTick = 0;

    private static DataKey<MoveToHealTargetGoal> RESCUE_DOG_AI = DataKey.make();

    private int healCooldown = 0;

    public RescueDogTalent(Talent talentIn, int levelIn) {
        super(talentIn, levelIn);
    }

    @Override
    public void init(AbstractDog dog) {
        if (!(dog instanceof Dog)) return;
        if (!dog.hasData(RESCUE_DOG_AI)) {
            var rescueDogGoal = new MoveToHealTargetGoal((Dog)dog, this);
            dog.goalSelector.addGoal(((Dog) dog).TALENT_GOAL_PRIORITY, rescueDogGoal);
            dog.setData(RESCUE_DOG_AI, rescueDogGoal);
        }
    }

    @Override
    public void livingTick(AbstractDog dog) {
        if (dog.level.isClientSide) {
            return;
        }

        if (this.healCooldown > 0 ) {
            --this.healCooldown;
        } else if (!dog.isInSittingPose() && (dog.getMode() == EnumMode.DOCILE) ) {
            if (this.nextSearchTick <= dog.tickCount) {
                this.searchCoolDown = 10;
                this.nextSearchTick = dog.tickCount + this.searchCoolDown;
    
                this.healTargets = this.getEntitiesToHeal(dog);
            }
        }

        

        // if (this.level() > 0) {
        //     LivingEntity owner = dog.getOwner();

        //     //TODO add particles and check how far away dog is
        //     if (owner != null && owner.getHealth() <= 6) {
        //         int healCost = this.healCost(this.level());

        //         if (dog.getDogHunger() >= healCost) {
        //             owner.heal(Mth.floor(this.level() * 1.5D));
        //             dog.setDogHunger(dog.getDogHunger() - healCost);
        //         }
        //     }
        // }
    }

    //TODO Decrease the healCost due to healing is more difficult now.
    public int healCost(AbstractDog dog, LivingEntity target) {
        int cost;
        if (this.level() >= 5) {
            cost = 10;
        } else {
            cost = 20;
        }

        

        return cost;
    }

    //TODO need tuned ? 
    public float healAmount(AbstractDog dog, LivingEntity target) {
        //Bonus level 3+
        float bonus = 0;

        float chance = dog.getRandom().nextFloat();

        /*
         * 10% : 3 Hearts
         * 20% : 1 Hearts
         * 30% : 0.5 Hearts
         * 40% : No
         */
        if (this.level() >= 3 )
            if (chance > 0.9) {
                bonus = 3;
            } else if (chance > 0.7) {
                bonus = 1;
            } else if (chance > 0.4) {
                bonus = 0.5f;
            }

        return Mth.floor(this.level() * 1.5D) + bonus*2;
    }

    @Override
    public boolean hasRenderer() {
        return true;
    }

    private void heal(AbstractDog dog, LivingEntity e) {
        if (this.healCooldown > 0) return;
        dog.setDogHunger(
            dog.getDogHunger() - this.healCost(dog, e)
        );
        e.heal(
            this.healAmount(dog, e)
        );
        if (dog.level instanceof ServerLevel) {
            ((ServerLevel) dog.level).sendParticles(
                ParticleTypes.HEART, 
                e.getX(), e.getY(), e.getZ(), 
                this.level*8, 
                e.getBbWidth(), 0.8f, e.getBbWidth(), 
                0.1
            );
        }
        this.healCooldown = dog.getRandom().nextInt(3) * 20; // Between 2 seconds
    } 

    private boolean isTargetLowHealth(AbstractDog dog, LivingEntity e) {
        return e.getHealth() <= 8.0;
    }

    private boolean canAffordToHealTarget(AbstractDog dog, LivingEntity e) {
        return dog.getDogHunger() - 10 >= this.healCost(dog, e);
    }

    private boolean canHealTarget(AbstractDog dog, LivingEntity e) {
        return (
            dog.distanceToSqr(e) <= 5
            && dog.hasLineOfSight(e)
        );
    }

    private List<LivingEntity> getEntitiesToHeal(AbstractDog dog) {
        return dog.level.getEntitiesOfClass(LivingEntity.class, dog.getBoundingBox().inflate(SEARCH_RADIUS), 
            e -> 
            
                //Low health entities
                (this.isTargetLowHealth(dog, e)) && (

                    // is Dog's Owner
                    (dog.getOwner() == e) 
                    
                    //is dog of the same owner
                    || ( 
                        e instanceof AbstractDog
                        && ((AbstractDog)e).getOwner() == dog.getOwner()
                    )

                    //is wolf of the same owner
                    || ( 
                        e instanceof Wolf
                        && ((Wolf)e).getOwner() == dog.getOwner()
                    )

                //can see target
                ) && (dog.hasLineOfSight(e))

        );
    }

    private LivingEntity selectHealTarget(AbstractDog dog) {
        if (this.healTargets.isEmpty()) return null;

        var target = this.healTargets.get(0); 
        double mindistanceSqr = target.distanceToSqr(dog);
        
        for (var i : this.healTargets) {
            if (dog.getOwner() == i) return i;
            else {
                var d = i.distanceToSqr(dog);
                if (d < mindistanceSqr) {
                    target = i;
                    mindistanceSqr = d;
                }
            }
        }

        //ChopinLogger.l("target : " +target);

        return target;

    }


    //TODO Option to disable Wolf and Dog healing and also add particles
    public static class MoveToHealTargetGoal extends Goal {

        private Dog dog;
        private RescueDogTalent talentInst;
        private LivingEntity target;

        private int ticksUntilPathRecalc = 0;

        private final int stopDist = 2;    

        public MoveToHealTargetGoal(Dog dog, RescueDogTalent talentInst) {
            this.dog = dog;
            this.talentInst = talentInst;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.dog.isInSittingPose()) return false;
            if (this.dog.getMode() != EnumMode.DOCILE) return false;
            this.target = this.talentInst.selectHealTarget(this.dog);
            if (target == null) return false;
            if (!this.talentInst.isTargetLowHealth(dog, target)) return false;
            if (!this.talentInst.canAffordToHealTarget(dog, target)) return false;            
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            if (this.dog.isInSittingPose()) return false;
            if (this.dog.getMode() != EnumMode.DOCILE) return false;
            if (target == null) return false;
            if (!this.talentInst.isTargetLowHealth(dog, target)) return false;
            if (!this.talentInst.canAffordToHealTarget(dog, target)) return false; 
            if (!this.stillValidTarget()) return false;

            return true;
        }
        

        @Override
        public void tick() {
            
            if (this.dog.distanceToSqr(this.target) > stopDist*stopDist) {

                this.dog.getLookControl().setLookAt(target, 10.0F, this.dog.getMaxHeadXRot());
                if (--this.ticksUntilPathRecalc <= 0) {
                    this.ticksUntilPathRecalc = 10;
                    if (!this.dog.isLeashed() && !this.dog.isPassenger()) {
                        //TODO ?
                        // if (this.dog.distanceToSqr(this.target) >= 144.0D) {
                        //     DogUtil.guessAndTryToTeleportToOwner(dog, 4);
                        // } else {
                            this.dog.getNavigation().moveTo(this.target, 1);
                        //}
                    }
                }
            } else {
                if (this.talentInst.canHealTarget(dog, target))
                    this.talentInst.heal(dog, target);
            }
        }

        private boolean stillValidTarget() {
            return (
                this.target.isAlive()
            );
        }


    }

}

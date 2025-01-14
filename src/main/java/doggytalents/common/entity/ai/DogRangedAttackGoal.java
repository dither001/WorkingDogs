package doggytalents.common.entity.ai;

import java.util.EnumSet;

import doggytalents.api.impl.IDogRangedAttackManager.UsingWeaponContext;
import doggytalents.common.entity.Dog;
import doggytalents.common.entity.Dog.CombatReturnStrategy;
import doggytalents.common.entity.Dog.LowHealthStrategy;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class DogRangedAttackGoal extends Goal {
    
    private final Dog dog;
    private int attackRadius;
    
    private final int timeOutTick = 40;
    private int waitingTick;
    private BlockPos.MutableBlockPos dogPos0;

    private int seeTime = 0;
    private int attackCooldown = 0;
    private int tickTillPathRecalc = 0;

    public DogRangedAttackGoal(Dog dog ) {
        this.dog = dog;
        this.attackRadius = 20;
        
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.dog.getMode().shouldAttack()) return false;

        if (
            this.dog.isDogLowHealth() 
            && this.dog.getLowHealthStrategy() == LowHealthStrategy.RUN_AWAY
        ) {
            return false;
        }

        if (this.dog.fallDistance > 7) return false;
        
        boolean restriction = false;
        if (this.dog.getMode().shouldFollowOwner() 
            && this.dog.getCombatReturnStrategy() != CombatReturnStrategy.NONE) {
            var owner = this.dog.getOwner();

            if (owner != null) {
                if (this.dog.distanceToSqr(owner) > this.getMaxDistanceAwayFromOwner()) 
                return false;
            }
        } else {
            restriction = !this.dog.patrolTargetLock();
        }

        var target = this.dog.getTarget();
        if (target == null) {
            return false;
        } else if (!target.isAlive()) {
            this.dog.setTarget(null); // Disacrd dead target no matter what
            return false;
        } else if (!this.dog.getDogRangedAttack().isApplicable(this.dog)) { 
            return false; 
        } else if (restriction && checkRestriction(target, dog)) {
            return false;
        }
        
        return true;
    }

    

    @Override
    public boolean canContinueToUse() {
        if (!this.dog.getMode().shouldAttack()) return false;

        if (!this.dog.getDogRangedAttack().isApplicable(this.dog)) return false;

        if (this.dog.fallDistance > 7) return false;

        boolean restriction = false;
        if (this.dog.getMode().shouldFollowOwner() 
            && this.dog.getCombatReturnStrategy() != CombatReturnStrategy.NONE) {
            var owner = this.dog.getOwner();

            if (owner != null) {
                if (this.dog.distanceToSqr(owner) > this.getMaxDistanceAwayFromOwner()) 
                return false;
            }
        } else {
            restriction = !this.dog.patrolTargetLock();
        }
        
        if (this.waitingTick > this.timeOutTick) return false;
        
        LivingEntity livingentity = this.dog.getTarget();

        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (!this.dog.getDogRangedAttack().isApplicable(this.dog)) { 
            return false; 
        } else if (restriction && checkRestriction(livingentity, dog)) {
            return false;
        } else {
            return !(livingentity instanceof Player)
                || !livingentity.isSpectator() && !((Player) livingentity).isCreative();
        }
    }

    private boolean checkRestriction(LivingEntity target, Dog dog) {
        var restrict_pos = dog.getRestrictCenter();
        if (restrict_pos == null)
            return false;
        var restrict_r = dog.getRestrictRadius();
        if (restrict_r < 0)
            return false;
        var max_dist = attackRadius - 2 + restrict_r; 
        if (target.distanceToSqr(Vec3.atBottomCenterOf(restrict_pos)) <= max_dist * max_dist)
            return false;
        return true;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    protected double getMaxDistanceAwayFromOwner() {
        if (this.dog.getCombatReturnStrategy() == CombatReturnStrategy.FAR)
           return 32*32;
        return 20*20;
    }

    @Override
    public void start() {
        this.seeTime = 0;
        this.tickTillPathRecalc = 0;
        this.dogPos0 = this.dog.blockPosition().mutable();
        this.dog.getDogRangedAttack().onStart(dog);
    }
    
    @Override
    public void stop() {
        this.seeTime = 0;
        this.attackCooldown = -1;
        this.dog.getDogRangedAttack().onStop(dog);
        this.dog.getNavigation().stop();
    }

    @Override
    public void tick() {
        var target = this.dog.getTarget();
        if (target == null)
            return;
        
        double d_dog_target_sqr = this.dog.distanceToSqr(target.getX(), target.getY(), target.getZ());
        boolean can_see_target = this.dog.getSensing().hasLineOfSight(target);
        
        updateSeeTime(can_see_target);

        if (dog.canDogFly()) {
            flyAndShootTarget(target, d_dog_target_sqr, can_see_target);
        } else {
            boolean can_shoot_target = 
                d_dog_target_sqr <= (double)(this.attackRadius * this.attackRadius);
            if (!can_shoot_target) {
                chaseTarget(target);
            } else {
                strafeAndShootTarget(target, d_dog_target_sqr, can_see_target, false);
            }
        }
        

    }

    private void flyAndShootTarget(LivingEntity target, double d_dog_target_sqr, boolean can_see_target) {
        boolean can_shoot_target = 
                d_dog_target_sqr <= (double)(this.attackRadius * this.attackRadius);
        
        double stop_chase_dist = 6 + target.getBbWidth()/2;

        if (d_dog_target_sqr > stop_chase_dist * stop_chase_dist)
            chaseTarget(target);
        else {
            this.waitingTick = 0;
            dog.getNavigation().stop();
        }

        if (can_shoot_target)
            strafeAndShootTarget(target, d_dog_target_sqr, can_see_target, true);
    }

    private void updateSeeTime(boolean canSeeTarget) {
        boolean was_seeing_target = this.seeTime > 0;
        if (canSeeTarget != was_seeing_target) {
           this.seeTime = 0;
        }
  
        if (canSeeTarget) {
           ++this.seeTime;
        } else {
           --this.seeTime;
        }
    }

    private void chaseTarget(LivingEntity target) {
        var dog_bp = dog.blockPosition();
        if (dog_bp.equals(this.dogPos0)) {
            ++this.waitingTick;
        } else {
            this.waitingTick = 0;
            this.dogPos0.set(dog_bp.getX(), dog_bp.getY(), dog_bp.getZ());
        }
        this.dog.getLookControl().setLookAt(target, 30.0F, this.dog.getMaxHeadXRot());
        if (--tickTillPathRecalc <= 0) {
            this.tickTillPathRecalc = 10;
            this.dog.getNavigation().moveTo(target, 1f);
        }
    }

    private void strafeAndShootTarget(LivingEntity target, double d_dog_target_sqr, boolean can_see_target, boolean fly) {
        if (!fly) {
            this.dog.getNavigation().stop();
            this.waitingTick = 0;
        }

        this.dog.getLookControl().setLookAt(target, 30.0F, this.dog.getMaxHeadXRot());
        this.dog.lookAt(target, 30.0F, this.dog.getMaxHeadXRot());
        if (!fly)
            strafeIfNeccessary(target, d_dog_target_sqr);

        updateUsingWeapon(can_see_target, target);
    }

    private void updateUsingWeapon(boolean can_see_target, LivingEntity target) {
        if (this.attackCooldown > 0)
            --this.attackCooldown;

        boolean attacked = this.dog.getDogRangedAttack().updateUsingWeapon(
            new UsingWeaponContext(dog, can_see_target, seeTime, attackCooldown, target)
        );
        
        if (attacked)
            this.attackCooldown = 20;
    }

    private void strafeIfNeccessary(LivingEntity target, double d_dog_target_sqr) {
        int strafe_dir = 0;

        double foward_threshold = this.attackRadius * 0.75F;
        double backup_threshold = 6;
        if (d_dog_target_sqr > foward_threshold * foward_threshold) {
            strafe_dir = 1;
        } else if (d_dog_target_sqr < backup_threshold * backup_threshold) {
            strafe_dir = -1;
        }

        this.dog.getMoveControl().strafe(strafe_dir * 0.5f, 0);
    }

}

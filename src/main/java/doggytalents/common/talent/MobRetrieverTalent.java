package doggytalents.common.talent;

import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.api.registry.Talent;
import doggytalents.api.registry.TalentInstance;
import doggytalents.common.config.ConfigHandler;
import doggytalents.common.entity.Dog;
import doggytalents.common.entity.ai.triggerable.TriggerableAction;
import doggytalents.common.register.DoggyTags;
import doggytalents.common.register.DoggyTalents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MobRetrieverTalent extends TalentInstance {

    private LivingEntity targetEntity;
    
    public MobRetrieverTalent(Talent talentIn, int levelIn) {
        super(talentIn, levelIn);
    }

    @Override
    public void tick(AbstractDog dogIn) {
        if (dogIn.level().isClientSide)
            return;
        if (!(dogIn instanceof Dog dog))
            return;
        updateRidingPassanger(dog);
        updateAndSetRetrieveTarget(dog); 
    }

    private void updateRidingPassanger(Dog dog) {
        var riding = dog.getFirstPassenger();
        if (riding == null)
            return;
        if (riding instanceof Player)
            return;
        if (!canStillRideTarget(dog, riding))
            dog.unRide();
    }

    private void updateAndSetRetrieveTarget(Dog dog) {
        if (this.targetEntity == null)
            return;
        var target = targetEntity;
        this.targetEntity = null;
        if (!isValidTarget(dog, target))
            return;
        if (dog.isBusy() || !readyForNewRider(dog))
            return;
        if (dog.isOrderedToSit())
            return;
        dog.triggerAction(new DogRetrieveMobAction(dog, target, this));
    }

    @Override
    public void set(AbstractDog dog, int levelBefore) {
        if (dog.level().isClientSide) return;
        if (levelBefore > 0 && this.level() <= 0) {
            dog.unRide();
        }
    }

    public boolean isValidTarget(Dog dog, LivingEntity target) {
        if (target == dog)
            return false;
        if (target.distanceToSqr(dog) >= getSelectTargetRange() * getSelectTargetRange())
            return false;
        if (!target.isAlive())
            return false;
        if (target.isSpectator())
            return false;
        if (target.isVehicle())
            return false;
        boolean onlyDog = ConfigHandler.SERVER.MOB_RETRIEVER_ONLY_CARRY_DOG.get();
        if (onlyDog && !(target instanceof Dog))
            return false;
        if (!(target instanceof LivingEntity))
            return false;
        if (target instanceof Player)
            return false;
        if (target instanceof Enemy)
            return false;
        if (target.getType().is(DoggyTags.MOB_RETRIEVER_MUST_IGNORE))
            return false;
        if (target instanceof TamableAnimal otherDog 
            && dog.getOwnerUUID() != null
            && ObjectUtils.notEqual(otherDog.getOwnerUUID(), dog.getOwnerUUID()))
            return false;
        if (!canLevelRideTarget(dog, target))
            return false;
        return true;
    }

    

    private boolean canStillRideTarget(Dog dog, Entity target) {
        if (!target.isAlive())
            return false;
        if (target.isSpectator())
            return false;
        if (target.isVehicle())
            return false;
        boolean onlyDog = ConfigHandler.SERVER.MOB_RETRIEVER_ONLY_CARRY_DOG.get();
        if (onlyDog && !(target instanceof Dog))
            return false;
        if (dog.isInSittingPose())
            return false;
        if (!dog.isDoingFine())
            return false;
        if (!(target instanceof LivingEntity))
            return false;
        if (target instanceof Enemy)
            return false;
        if (target.getType().is(DoggyTags.MOB_RETRIEVER_MUST_IGNORE))
            return false;
        if (target instanceof TamableAnimal otherDog 
            && dog.getOwnerUUID() != null
            && ObjectUtils.notEqual(otherDog.getOwnerUUID(), dog.getOwnerUUID()))
            return false;
        if (!canLevelRideTarget(dog, target))
            return false;
        return true;
    }

    @Override
    public InteractionResultHolder<Float> gettingAttackedFrom(AbstractDog dog, DamageSource source, float damage) {
        if (!dog.level().isClientSide)
            maybeDropRiding(dog);
        return super.gettingAttackedFrom(dog, source, damage);
    }

    private void maybeDropRiding(AbstractDog dog) {
        if (!dog.isVehicle())
            return;
        var passenger = dog.getFirstPassenger();
        if (passenger == null)
            return;
        if (passenger instanceof Player)
            return;
        if ((passenger instanceof TamableAnimal otherDog) 
            && otherDog.getOwner() == dog.getOwner()) {
            return;
        }
        dog.unRide();
    }

    private boolean canLevelRideTarget(Dog dog, Entity target) {
        int lvl = this.level();
        float target_bbW = target.getBbWidth();
        float target_bbH = target.getBbHeight();
        float w_ratio = target_bbW / dog.getDogVisualBbWidth();
        float h_ratio = target_bbH / dog.getDogVisualBbHeight();
        if (target_bbW >= 1f)
            return false;
        if (w_ratio > 1.2)
            return false;
        if (lvl >= 5)
            return h_ratio <= 2;
        if (lvl >= 3)
            return h_ratio <= 1.5;
        return h_ratio <= 1;
    }

    private boolean readyForNewRider(Dog dog) {
        return dog.isDoingFine() && !dog.isInSittingPose() && !dog.isVehicle();
    }

    public void setTarget(Dog dog, LivingEntity target) {
        if (!isValidTarget(dog, target)) return;
        this.targetEntity = target;
    }

    public static Optional<Dog> chooseNearestDog(Player player, Level level) {
        var valid_dogs = level.getEntitiesOfClass(
            Dog.class, 
            player.getBoundingBox()
                .inflate(4, 2, 4),
            d -> {
                if (!d.isDoingFine())
                    return false;
                if (d.getDogLevel(DoggyTalents.MOB_RETRIEVER) <= 0)
                    return false;
                if (d.isBusy())
                    return false;
                return true;
            }    
        );
        if (valid_dogs.isEmpty())
            return Optional.empty();
        double min_dist = valid_dogs.get(0).distanceToSqr(player);
        Dog choosen_dog = valid_dogs.get(0);
        for (var dog : valid_dogs) {
            double dist = dog.distanceToSqr(player);
            if (dist < min_dist) {
                min_dist = dist;
                choosen_dog = dog;
            }
        }

        return Optional.of(choosen_dog);
    }

    @Override
    public InteractionResult blockIdleAnim(AbstractDog dogIn) {
        if (dogIn.isVehicle()) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public static int getSelectTargetRange() {
        return 20;
    }

    public static class DogRetrieveMobAction extends TriggerableAction {

        private MobRetrieverTalent inst;
        private LivingEntity target;
        
        private int tickTillPathRecalc;
        private int stopDist = 3;
        private boolean isBringingBack = false;
        private LivingEntity owner = null;

        public DogRetrieveMobAction(Dog dog, LivingEntity target, MobRetrieverTalent inst) {
            super(dog, false, false);
            this.target = target;
            this.inst = inst;
        }

        @Override
        public void onStart() {
        }

        @Override
        public void tick() {
            if (!inst.isValidTarget(dog, target)) {
                this.setState(ActionState.FINISHED);
                return;
            }
            if (!this.isBringingBack && !this.dog.getPassengers().isEmpty()) {
                this.setState(ActionState.FINISHED);
                return;
            }
            if (this.isBringingBack && this.dog.getPassengers().isEmpty()) {
                this.setState(ActionState.FINISHED);
                return;
            }
            if (this.isBringingBack && owner == null) {
                this.setState(ActionState.FINISHED);
                return;
            }
            if (this.isBringingBack && (!ownerIsStillThere() || returnedToOwner())) {
                this.setState(ActionState.FINISHED);
                return;
            }
            if (this.isBringingBack)
                returnToOwner();
            else
                goGetMob();
        }

        private void goGetMob() {
            if (this.dog.distanceToSqr(this.target) > stopDist*stopDist) {

                this.dog.getLookControl().setLookAt(target, 10.0F, this.dog.getMaxHeadXRot());
                if (--this.tickTillPathRecalc <= 0) {
                    this.tickTillPathRecalc = 10;
                    if (!this.dog.isLeashed() && !this.dog.isPassenger()) {
                        //A Valid target is not that far away and is checked above.
                        //if (dog.distanceToSqr(target) > 400) return;
                        this.dog.getNavigation().moveTo(this.target, this.dog.getUrgentSpeedModifier());
                    }
                }
            } else {
                this.dog.getNavigation().stop();
                target.startRiding(dog);
                owner = dog.getOwner();
                this.isBringingBack = true;
            }
        }

        private void returnToOwner() {
            if (this.dog.distanceToSqr(this.owner) > stopDist*stopDist) {

                this.dog.getLookControl().setLookAt(owner, 10.0F, this.dog.getMaxHeadXRot());
                if (--this.tickTillPathRecalc <= 0) {
                    this.tickTillPathRecalc = 10;
                    if (!this.dog.isLeashed() && !this.dog.isPassenger()) {
                        //A Valid target is not that far away and is checked above.
                        //if (dog.distanceToSqr(target) > 400) return;
                        this.dog.getNavigation().moveTo(this.owner, this.dog.getUrgentSpeedModifier());
                    }
                }
            }
        }

        @Override
        public void onStop() {
            
        }

        private boolean ownerIsStillThere() {
            if (!owner.isAlive())
                return false;
            if (owner.isSpectator())
                return false;
            if (owner.distanceToSqr(dog) > 16 * 16)
                return false;
            return true;
        }
        
        private boolean returnedToOwner() {
            return dog.distanceToSqr(owner) <= stopDist * stopDist;
        }

    }
    
}

package doggytalents.common.entity.ai;

import java.util.EnumSet;

import doggytalents.common.entity.Dog;
import net.minecraft.world.entity.ai.goal.Goal;

public class DogSitWhenOrderedGoal extends Goal {

    Dog dog;

    //Fix dog repeatedly sitting and standing if
    //owner logged out without orderingDogToSit.
    private boolean ownerOffline;

    public DogSitWhenOrderedGoal(Dog dog) {
        this.dog = dog;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (dog.isPassenger()) return true;
        if (!dog.forceSit() && dog.dogAi.isActionBlockingSit())
            return false;
        //Passaenger dog always wants to sit down.
        if (!dog.onGround())
            return false;
        if (dog.isInWaterOrBubble())
            return false;
        return dog.isOrderedToSit();
    }

    @Override
    public void start() {
        dog.dogAi.clearTriggerableAction();

        //Fix dog repeatedly sitting and standing if
        //owner logged out without orderingDogToSit.
        this.ownerOffline = this.dog.getOwner() == null;

        this.dog.getNavigation().stop();
        this.dog.setInSittingPose(true);
    }

    @Override
    public boolean canContinueToUse() {
        if (this.dog.isPassenger()) {
            return true;
        }
        if (this.dog.fallDistance > 3)
            return false;
        if (this.dog.isOrderedToSit()) {
            return true;
        }

        //Fix dog repeatedly sitting and standing if
        //owner logged out without orderingDogToSit.
        if (this.ownerOffline) {
            this.ownerOffline = this.dog.getOwner() == null;
        }
        return ownerOffline;
    }

    @Override
    public void stop() {
        this.dog.setInSittingPose(false);
    }
    
}

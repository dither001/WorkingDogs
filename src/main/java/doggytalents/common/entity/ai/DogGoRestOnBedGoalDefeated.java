package doggytalents.common.entity.ai;

import java.util.EnumSet;

import doggytalents.ChopinLogger;
import doggytalents.DoggyBlocks;
import doggytalents.common.entity.Dog;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class DogGoRestOnBedGoalDefeated extends Goal {

    private final int SEARCH_RADIUS = 5;

    private Dog dog;
    private int tickTillSearch = 10;
    private int tickTillPathRecalc = 0;
    private BlockPos targetBed;

    public DogGoRestOnBedGoalDefeated(Dog dog) {
        this.dog = dog;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.dog.isDefeated())
            return false;
        if (this.dog.isOrderedToSit()) 
            return false;
        targetBed = null;
        if (--tickTillSearch <= 0) {
            tickTillSearch = 10;
            targetBed = this.searchForBed();
        }
        if (targetBed == null)
            return false;
    
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (!this.dog.isDefeated())
            return false;
        if (this.dog.isOrderedToSit()) 
            return false;
        if (this.targetBed == null)
            return false;
        return true;
    }

    @Override
    public void start() {
        this.tickTillPathRecalc = 0;
        this.dog.setInSittingPose(false);
    }

    @Override
    public void stop() {
        this.dog.getNavigation().stop();
        this.dog.setInSittingPose(false);
    }

    @Override
    public void tick() {
        if (this.targetBed == null)
            return;
        var nav = dog.getNavigation();
        var d_targetBed = dog.distanceToSqr(Vec3.atBottomCenterOf(targetBed));
        var onBed = d_targetBed < 0.35;
        if (!onBed && --this.tickTillPathRecalc <= 0) {
            this.tickTillPathRecalc = 10;
            nav.moveTo(targetBed.getX() + 0.5, 
                targetBed.getY(), targetBed.getZ() + 0.5, 1);
        }   
        if (  
            nav.isDone() && !onBed
        ) {
            ChopinLogger.l("move!");
            dog.getMoveControl().setWantedPosition(targetBed.getX() + 0.5, 
            targetBed.getY(), targetBed.getZ() + 0.5, 1);
        }
        this.dog.setInSittingPose(d_targetBed < 1);
        if (this.dog.tickCount % 2 != 0) return;
        var state = this.dog.level.getBlockState(targetBed);
        if (!isBed(state)) {
            this.targetBed = null;
        }
    }

    private BlockPos searchForBed() {
        var dog_b0 = dog.blockPosition();
        for (var bpos : BlockPos.betweenClosed(
            dog_b0.offset(-SEARCH_RADIUS, -1, -SEARCH_RADIUS), 
            dog_b0.offset(SEARCH_RADIUS, 1, SEARCH_RADIUS))
        ) {
            var state = dog.level.getBlockState(bpos);
            if (isBed(state))
                return bpos;
        }
        return null;
    }

    private boolean isBed(BlockState state) {
        var block = state.getBlock();
        if (block == DoggyBlocks.DOG_BED.get())
            return true;
        if (state.is(BlockTags.BEDS))
            return true;
        return false;
    }
    
}

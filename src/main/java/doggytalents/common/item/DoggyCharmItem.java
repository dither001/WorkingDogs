package doggytalents.common.item;

import java.util.Objects;

import doggytalents.common.entity.Dog;
import doggytalents.common.event.EventHandler;
import doggytalents.common.register.DoggyEntityTypes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class DoggyCharmItem extends Item {

    public DoggyCharmItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (world.isClientSide || !(world instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            Player player = context.getPlayer();
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction enumfacing = context.getClickedFace();
            BlockState iblockstate = world.getBlockState(blockpos);

            if (player == null)
                return InteractionResult.SUCCESS;

            if (!EventHandler.isWithinTrainWolfLimit(player))
                return InteractionResult.SUCCESS;

            BlockPos blockpos1;
            if (iblockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(enumfacing);
            }


            Entity entity = DoggyEntityTypes.DOG.get().spawn((ServerLevel) world, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, !Objects.equals(blockpos, blockpos1) && enumfacing == Direction.UP, false);
            if (entity instanceof Dog) {
               Dog dog = (Dog)entity;
               if (player != null) {
                   dog.setTame(true);
                   dog.setOwnerUUID(player.getUUID());
                   dog.maxHealth();
               }
               itemstack.shrink(1);
               if (player instanceof ServerPlayer sP) {
                   CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(sP, blockpos1, itemstack);
                   sP.getCooldowns().addCooldown(this, 30);
               }
            
           }

           return InteractionResult.SUCCESS;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (worldIn.isClientSide || !(worldIn instanceof ServerLevel)) {
            return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
        } else {
            if (playerIn == null)
                return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);;
            if (!EventHandler.isWithinTrainWolfLimit(playerIn))
                return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
            
            HitResult raytraceresult = Item.getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
            if (raytraceresult != null && raytraceresult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockpos = ((BlockHitResult)raytraceresult).getBlockPos();
                if (!(worldIn.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                    return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
                } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, ((BlockHitResult)raytraceresult).getDirection(), itemstack)) {
                    Entity entity = DoggyEntityTypes.DOG.get().spawn((ServerLevel) worldIn, itemstack, playerIn, blockpos, MobSpawnType.SPAWN_EGG, false, false);
                    if (entity instanceof Dog) {
                        Dog dog = (Dog)entity;
                           dog.setTame(true);
                           dog.setOwnerUUID(playerIn.getUUID());
                           dog.maxHealth();
                           itemstack.shrink(1);

                        if (playerIn instanceof ServerPlayer sP)
                            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(sP, blockpos, itemstack);
                        playerIn.awardStat(Stats.ITEM_USED.get(this));
                        
                        playerIn.getCooldowns().addCooldown(this, 30);
                        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
                    } else {
                        return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
                    }
                } else {
                    return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
                }
            } else {
                return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
            }
        }
    }
}

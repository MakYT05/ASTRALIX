package org.zeith.modid.custom.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EnderSwordItem extends SwordItem
{
    public EnderSwordItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Item.Properties properties)
    {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        if (!world.isClientSide && player instanceof ServerPlayer serverPlayer)
        {
            Vec3 lookVec = player.getLookAngle().scale(5);
            Vec3 targetPos = player.position().add(lookVec);

            player.getCooldowns().addCooldown(this, 100);
            BlockPos targetBlockPos = new BlockPos((int) targetPos.x(), (int) targetPos.y(), (int) targetPos.z());

            if (!world.isEmptyBlock(targetBlockPos))
            {
                BlockPos safePos = findSafePosition(world, player, targetBlockPos, lookVec);
                if (safePos != null)
                {
                    serverPlayer.teleportTo(safePos.getX() + 0.5, safePos.getY(), safePos.getZ() + 0.5);
                    return InteractionResultHolder.success(player.getItemInHand(hand));
                }
            }
            else
            {
                serverPlayer.teleportTo(targetPos.x(), targetPos.y(), targetPos.z());
                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private BlockPos findSafePosition(Level world, Player player, BlockPos targetBlockPos, Vec3 lookVec)
    {
        BlockPos previousPos = targetBlockPos.relative(player.getDirection().getOpposite());

        if (world.isEmptyBlock(previousPos))
        {
            return previousPos;
        }

        for (int y = -2; y <= 2; y++)
        {
            BlockPos pos = previousPos.offset(0, y, 0);
            if (world.isEmptyBlock(pos))
            {
                return pos;
            }
        }
        return null;
    }
}
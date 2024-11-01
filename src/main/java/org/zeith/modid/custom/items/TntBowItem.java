package org.zeith.modid.custom.items;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.client.mana.ManaOverlay;

public class TntBowItem extends BowItem
{
    public TntBowItem(Item.Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        if (!world.isClientSide && player instanceof ServerPlayer serverPlayer) {
            if (ManaOverlay.currentMana >= 30) {
                Vec3 lookVec = player.getLookAngle().scale(1.5);
                Vec3 spawnPos = player.position().add(lookVec);

                player.getCooldowns().addCooldown(this, 100);

                PrimedTnt tnt = new PrimedTnt(world, spawnPos.x, spawnPos.y + 1.5, spawnPos.z, player);
                tnt.setDeltaMovement(lookVec);

                world.addFreshEntity(tnt);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TNT_PRIMED, SoundSource.PLAYERS, 1.0F, 1.0F);

                player.getItemInHand(hand).hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));

                ManaOverlay.currentMana -= 30;

                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
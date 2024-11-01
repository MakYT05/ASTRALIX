package org.zeith.modid.custom.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.client.mana.ManaOverlay;
import org.zeith.modid.custom.entyties.LightningBoltProjectile;

public class LightningWandItem extends Item {
    public LightningWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (ManaOverlay.currentMana >= 25) {
                if (level instanceof ServerLevel serverLevel) {
                    Vec3 startPos = player.getEyePosition();
                    Vec3 lookVector = player.getLookAngle();

                    player.getCooldowns().addCooldown(this, 100);

                    LightningBoltProjectile projectile = new LightningBoltProjectile(serverLevel, player);
                    projectile.setPos(startPos.x, startPos.y, startPos.z);
                    projectile.shoot(lookVector.x, lookVector.y, lookVector.z, 1.5f, 0);
                    serverLevel.addFreshEntity(projectile);

                    stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                }
                ManaOverlay.currentMana -= 25;
            }
        }
        return InteractionResultHolder.success(stack);
    }
}
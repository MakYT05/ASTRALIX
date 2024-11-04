package org.zeith.modid.custom.items;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.client.mana.ManaOverlay;

import java.util.List;

public class MagicStick extends Item {
    public MagicStick(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (ManaOverlay.currentMana >= 10) {
                if (level instanceof ServerLevel serverLevel) {
                    Vec3 startPos = player.getEyePosition();
                    Vec3 lookVector = player.getLookAngle().scale(0.5);

                    player.getCooldowns().addCooldown(this, 100);
                    serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_SHOOT,
                            SoundSource.PLAYERS, 1.0F, 1.0F);

                    for (int i = 0; i < 20; i++) {
                        Vec3 particlePos = startPos.add(lookVector.scale(i));

                        serverLevel.sendParticles(ParticleTypes.FLAME, particlePos.x, particlePos.y, particlePos.z, 1, 0, 0, 0, 0);

                        AABB hitBox = new AABB(particlePos.x - 0.3, particlePos.y - 0.3, particlePos.z - 0.3,
                                particlePos.x + 0.3, particlePos.y + 0.3, particlePos.z + 0.3);

                        List<Entity> entities = serverLevel.getEntities(player, hitBox);

                        for (Entity entity : entities) {
                            if (entity instanceof LivingEntity target && entity != player) {
                                target.hurt(player.damageSources().magic(), 2.0F);
                                return InteractionResultHolder.success(stack);
                            }
                        }
                    }
                }
                ManaOverlay.currentMana -= 10;
            }
        }
        return InteractionResultHolder.success(stack);
    }
}
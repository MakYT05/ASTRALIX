package org.zeith.modid.custom.items;


import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.modid.Goal.FollowPlayerGoal;
import org.zeith.modid.client.mana.ManaOverlay;

import java.util.Timer;
import java.util.TimerTask;

public class AstralSword extends Item {

    public AstralSword(Properties properties) { super(properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (ManaOverlay.currentMana >= 30) {
                summonEndermen((ServerLevel) level, player);

                startScreenShake(3);

                player.getCooldowns().addCooldown(this, 10000);

                player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);

                ManaOverlay.currentMana -= 30;

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        summonEndermen((ServerLevel) level, player);
                        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    }
                }, 3000);
            }
        }
        return InteractionResultHolder.success(stack);
    }

    private void startScreenShake(int durationInSeconds) {
        MinecraftForge.EVENT_BUS.register(new Object() {
            private int ticks = 0;
            private final int shakeDuration = durationInSeconds * 20;

            @OnlyIn(Dist.CLIENT)
            @SubscribeEvent
            public void onClientTick(TickEvent.ClientTickEvent event) {
                if (event.phase == TickEvent.Phase.END) {
                    ticks++;
                    if (ticks <= shakeDuration) {
                        applyScreenShake();
                    } else {
                        MinecraftForge.EVENT_BUS.unregister(this);
                    }
                }
            }

            @OnlyIn(Dist.CLIENT)
            private void applyScreenShake() {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    mc.player.setPos(mc.player.getX() + (Math.random() - 0.5) * 0.1, mc.player.getY(), mc.player.getZ() + (Math.random() - 0.5) * 0.1);
                }
            }
        });
    }

    private void summonEndermen(ServerLevel level, Player player) {
        for (int i = 0; i < 5; i++) {
            EnderMan enderman = EntityType.ENDERMAN.create(level);

            if (enderman != null) {
                BlockPos spawnPos = player.blockPosition().offset(level.random.nextInt(3) - 1, 0, level.random.nextInt(3) - 1);
                enderman.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

                enderman.goalSelector.addGoal(1, new FollowPlayerGoal(enderman, 1.0, 10.0F));

                enderman.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(enderman, LivingEntity.class, 10, true, false,
                        entity -> entity != player && entity.isAlive() && !(entity instanceof EnderMan) && entity.distanceTo(player) <= 10));

                level.addFreshEntity(enderman);
            }
        }
    }
}
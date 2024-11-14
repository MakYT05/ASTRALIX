package org.zeith.modid.custom.items;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.modid.Goal.FollowPlayerGoal;
import org.zeith.modid.client.mana.ManaOverlay;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AstralSword extends SwordItem {

    public AstralSword(Tier tier, int attackDamageModifier, float attackSpeedModifier, Item.Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (ManaOverlay.currentMana >= 30) {
                summonEndermen(level, player);
                startScreenShake(3);
                player.getCooldowns().addCooldown(this, 10000);
                player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                ManaOverlay.currentMana -= 30;

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        summonEndermen(level, player);
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

            private void applyScreenShake() {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    mc.player.setPos(mc.player.getX() + (Math.random() - 0.5) * 0.1, mc.player.getY(), mc.player.getZ() + (Math.random() - 0.5) * 0.1);
                }
            }
        });
    }

    private void summonEndermen(Level level, Player player) {
        for (int i = 0; i < 5; i++) {
            EnderMan enderman = EntityType.ENDERMAN.create(level);

            if (enderman != null) {
                BlockPos spawnPos = player.blockPosition().offset(level.random.nextInt(3) - 1, 0, level.random.nextInt(3) - 1);
                enderman.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

                enderman.goalSelector.addGoal(1, new FollowPlayerGoal(enderman, 1.0, 10.0F));

                enderman.goalSelector.addGoal(2, new AttackEntitiesInRadiusGoal(enderman, 10.0));

                level.addFreshEntity(enderman);
            }
        }
    }

    public static class AttackEntitiesInRadiusGoal extends net.minecraft.world.entity.ai.goal.Goal {
        private final EnderMan enderman;
        private final double radius;

        public AttackEntitiesInRadiusGoal(EnderMan enderman, double radius) {
            this.enderman = enderman;
            this.radius = radius;
        }

        @Override
        public boolean canUse() {
            List<net.minecraft.world.entity.Entity> nearbyEntities = this.enderman.getCommandSenderWorld().getEntities(this.enderman, this.enderman.getBoundingBox().inflate(radius));

            for (net.minecraft.world.entity.Entity entity : nearbyEntities) {
                if (entity != this.enderman && !(entity instanceof Player)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void tick() {
            List<net.minecraft.world.entity.Entity> nearbyEntities = this.enderman.getCommandSenderWorld().getEntities(this.enderman, this.enderman.getBoundingBox().inflate(radius));

            for (net.minecraft.world.entity.Entity entity : nearbyEntities) {
                if (entity != this.enderman && !(entity instanceof Player)) {
                    this.enderman.getNavigation().moveTo(entity.getX(), entity.getY(), entity.getZ(), 1.0);
                    this.enderman.swing(InteractionHand.MAIN_HAND);

                    break;
                }
            }
        }
    }
}
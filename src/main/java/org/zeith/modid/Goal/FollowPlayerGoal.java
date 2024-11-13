package org.zeith.modid.Goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {
    private final EnderMan enderman;
    private Player targetPlayer;
    private final double speedModifier;
    private final float maxDistance;

    public FollowPlayerGoal(EnderMan enderman, double speedModifier, float maxDistance) {
        this.enderman = enderman;
        this.speedModifier = speedModifier;
        this.maxDistance = maxDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.targetPlayer = this.enderman.level().getNearestPlayer(this.enderman, maxDistance);
        return this.targetPlayer != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPlayer != null && this.enderman.distanceTo(this.targetPlayer) > 2.0F && this.enderman.distanceTo(this.targetPlayer) < maxDistance;
    }

    @Override
    public void start() { this.enderman.getNavigation().moveTo(this.targetPlayer, this.speedModifier); }

    @Override
    public void stop() {
        this.targetPlayer = null;
        this.enderman.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.targetPlayer != null) {
            this.enderman.getNavigation().moveTo(this.targetPlayer, this.speedModifier);
        }
    }
}
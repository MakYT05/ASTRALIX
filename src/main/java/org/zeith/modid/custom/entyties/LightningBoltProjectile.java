package org.zeith.modid.custom.entyties;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.init.EntitiesMI;

public class LightningBoltProjectile extends ThrowableProjectile {
    private static final double SPEED = 2.5D;

    public LightningBoltProjectile(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public LightningBoltProjectile(Level level, Player player) {
        super(EntitiesMI.LIGHTNING_BALL, level);
        Vec3 lookDirection = player.getLookAngle();
        this.setDeltaMovement(lookDirection.scale(SPEED));
        this.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
    }

    @Override
    protected void onHit(HitResult result) {
        if (!level().isClientSide) {
            if (level() instanceof ServerLevel serverLevel) {
                Vec3 hitPos;
                if (result.getType() == HitResult.Type.BLOCK) {
                    hitPos = ((BlockHitResult) result).getLocation();
                } else if (result.getType() == HitResult.Type.ENTITY) {
                    hitPos = ((EntityHitResult) result).getEntity().position();
                } else {
                    hitPos = this.position();
                }

                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
                if (lightningBolt != null) {
                    lightningBolt.moveTo(hitPos.x, hitPos.y, hitPos.z);
                    serverLevel.addFreshEntity(lightningBolt);
                }
            }
            discard();
        }
    }

    @Override
    public void tick() {
        Vec3 direction = this.getDeltaMovement();

        this.setPos(this.getX() + direction.x, this.getY() + direction.y, this.getZ() + direction.z);

        if (this.isInWater()) {
            this.discard();
        }
        this.setNoGravity(true);

        super.tick();
    }

    @Override
    protected void defineSynchedData() {}
}
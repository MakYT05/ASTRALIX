package org.zeith.modid.custom.entyties;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.init.EntitiesMI;

public class Meteorite extends ThrowableProjectile {
    private static final double SPEED = 2.5D;
    public static int RADIUS = 5;

    public Meteorite(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Meteorite(Level level, Player player) {
        super(EntitiesMI.METEORITE, level);

        Vec3 lookDirection = player.getLookAngle();

        this.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());

        this.shoot(lookDirection.x, lookDirection.y, lookDirection.z, (float) SPEED, 0);
    }

    @Override
    protected void onHit(HitResult result) {
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, Level.ExplosionInteraction.BLOCK);

            for (int x = -RADIUS; x <= RADIUS; x++) {
                for (int y = -RADIUS; y <= RADIUS; y++) {
                    for (int z = -RADIUS; z <= RADIUS; z++) {
                        if (this.level().getBlockState(this.blockPosition().offset(x, y, z)).isAir()) {
                            this.level().setBlockAndUpdate(this.blockPosition().offset(x, y, z), Blocks.FIRE.defaultBlockState());
                        }
                    }
                }
            }
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        this.setNoGravity(true);

        if (this.level().isClientSide) {
            RandomSource random = this.level().random;

            for (int i = 0; i < 2; i++) {
                double d0 = this.getX() + (random.nextDouble() - 0.5) * 0.2;
                double d1 = this.getY() + (random.nextDouble() - 0.5) * 0.2;
                double d2 = this.getZ() + (random.nextDouble() - 0.5) * 0.2;
                this.level().addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                this.level().addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }

        if (this.isInWater()) {
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData() {}
}
package org.zeith.modid.custom.entyties;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import org.zeith.modid.init.EntitiesMI;

public class SwordEnt extends ThrowableProjectile {
    private static final double THROW_SPEED = 2.0;
    private static final int DAMAGE = 10;
    private ItemStack itemStack;
    private Level level;

    public SwordEnt(EntityType<? extends ThrowableProjectile> entityType, Level level) { super(entityType, level); }

    public SwordEnt(Level level, Player player) {
        super(EntitiesMI.SWORD_ENT, level);

        this.setItemStack(itemStack);

        Vec3 lookDirection = player.getLookAngle();

        this.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());

        this.shoot(lookDirection.x, lookDirection.y, lookDirection.z, (float) THROW_SPEED, 0);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        if (result instanceof EntityHitResult entityHitResult) {
            Entity entity = entityHitResult.getEntity();
            Player player = (Player) this.getOwner();

            if (entity instanceof LivingEntity && entity != player && !(entity instanceof FakePlayer)) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.hurt(livingEntity.damageSources().playerAttack(player), DAMAGE);
                this.level.playSound(null, player.blockPosition(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        } else if (result.getType() == HitResult.Type.BLOCK) {
            this.level.playSound(null, this.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        this.remove(Entity.RemovalReason.DISCARDED);
    }
    public void setItemStack(ItemStack itemStack) { this.itemStack = itemStack; }
}
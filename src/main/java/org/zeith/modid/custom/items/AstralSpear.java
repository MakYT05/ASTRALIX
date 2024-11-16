package org.zeith.modid.custom.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.zeith.modid.client.mana.ManaOverlay;

import java.util.List;

public class AstralSpear extends SwordItem {

    public AstralSpear(Tier tier, int attackDamageModifier, float attackSpeedModifier, Item.Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (player != null && !level.isClientSide) {
            if (ManaOverlay.currentMana >= 20) {
                List<Entity> entities = level.getEntities(player, new AABB(player.blockPosition()).inflate(5), e -> e instanceof LivingEntity);

                for (Entity entity : entities) {
                    if (entity instanceof LivingEntity && entity != player) {
                        Vec3 direction = entity.position().subtract(player.position()).normalize();
                        entity.setDeltaMovement(direction.scale(1.5));

                        entity.hurt(player.damageSources().generic(), 5.0F);
                    }
                }
                ManaOverlay.currentMana -= 20;
            }
        }
        return InteractionResult.SUCCESS;
    }
}
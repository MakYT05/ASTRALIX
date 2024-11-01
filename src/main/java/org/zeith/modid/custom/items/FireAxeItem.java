package org.zeith.modid.custom.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.client.mana.ManaOverlay;

import java.util.List;

public class FireAxeItem extends AxeItem
{
    public FireAxeItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Item.Properties properties)
    {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        if (!world.isClientSide && player instanceof ServerPlayer) {
            if (ManaOverlay.currentMana >= 30) {
                Vec3 lookVec = player.getLookAngle();
                Vec3 playerPos = player.position();

                Vec3 targetPos = playerPos.add(lookVec.scale(5));
                BlockPos targetBlockPos = new BlockPos((int) targetPos.x(), (int) targetPos.y(), (int) targetPos.z());

                player.getCooldowns().addCooldown(this, 100);

                AABB area = new AABB(targetBlockPos).inflate(5);

                List<LivingEntity> entitiesInRange = world.getEntitiesOfClass(LivingEntity.class, area, entity -> entity != player);

                for (LivingEntity entity : entitiesInRange) {
                    entity.setSecondsOnFire(5);
                }
                ManaOverlay.currentMana -= 30;

                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
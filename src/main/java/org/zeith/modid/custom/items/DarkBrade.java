package org.zeith.modid.custom.items;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class DarkBrade extends SwordItem
{
    public DarkBrade(Tier tier, int attackDamageModifier, float attackSpeedModifier, Item.Properties properties)
    {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        if (!world.isClientSide && player instanceof ServerPlayer serverPlayer)
        {
            Vec3 lookVec = player.getLookAngle();
            Vec3 playerPos = player.position();
            Vec3 targetPos = playerPos.add(lookVec.scale(5));

            AABB searchBox = new AABB(playerPos, targetPos).inflate(1.0);

            List<Entity> entities = world.getEntities(player, searchBox, entity -> entity instanceof LivingEntity);

            for (Entity entity : entities)
            {
                if (entity instanceof LivingEntity livingEntity) { livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 3)); }
            }

            player.getCooldowns().addCooldown(this, 60);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
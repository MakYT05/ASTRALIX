package org.zeith.modid.custom.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import org.zeith.modid.client.mana.ManaOverlay;

import java.util.List;

public class AstralSword extends Item {
    private static final double THROW_SPEED = 2.0;
    private static final int DAMAGE = 10;

    public AstralSword(Properties properties) { super(properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            if (ManaOverlay.currentMana >= 30) {
                launchAstralSword(world, player, hand);
            }
            ManaOverlay.currentMana -= 30;
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    private void launchAstralSword(Level world, Player player, InteractionHand hand) {
        Vec3 lookVec = player.getLookAngle();
        Vec3 start = player.position().add(0, player.getEyeHeight(), 0);
        Vec3 end = start.add(lookVec.scale(THROW_SPEED));

        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, new AABB(start, end));
        for (LivingEntity entity : entities) {
            if (entity != player && !(entity instanceof FakePlayer)) {
                entity.hurt(entity.damageSources().playerAttack(player), DAMAGE);
                world.playSound(null, player.blockPosition(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.0F);

                returnItemToPlayer(player, hand);
                return;
            }
        }

        world.playSound(null, player.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
        returnItemToPlayer(player, hand);
    }

    private void returnItemToPlayer(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.getInventory().add(itemstack.copy());
        itemstack.shrink(1);
    }
}
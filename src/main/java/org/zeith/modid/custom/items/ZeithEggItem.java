package org.zeith.modid.custom.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.core.BlockPos;
import org.zeith.modid.client.mana.ManaOverlay;

public class ZeithEggItem extends Item {
    private final EntityType<?> type;

    public ZeithEggItem(EntityType<?> type, Item.Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();

        if (!world.isClientSide()) {
            if (ManaOverlay.currentMana >= 100) {
                ServerLevel serverWorld = (ServerLevel) world;
                BlockPos clickedPos = context.getClickedPos();
                Player player = context.getPlayer();

                type.spawn(serverWorld, null, player, clickedPos, MobSpawnType.SPAWN_EGG, true, false);

                if (player != null) {
                    applyGoldenAppleEffects(player);
                }

                context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));

                ManaOverlay.currentMana -= 100;
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    private void applyGoldenAppleEffects(Player player)
    {
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, Integer.MAX_VALUE, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, Integer.MAX_VALUE, 3, false, false));
    }
}
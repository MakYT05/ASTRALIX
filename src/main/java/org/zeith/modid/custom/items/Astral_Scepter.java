package org.zeith.modid.custom.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.client.ability.KeyInputHandler;
import org.zeith.modid.client.mana.ManaOverlay;
import org.zeith.modid.custom.entyties.Knife;
import org.zeith.modid.custom.entyties.Meteorite;

public class Astral_Scepter extends Item {
    private static final int MANA_COST_METEOR = 50;
    private static final int MANA_COST_KNIFE = 20;

    public Astral_Scepter(Properties properties) { super(properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    public void releaseUsing(ItemStack stack, Level level, Player player, int timeLeft) {
        if (!level.isClientSide) {
            int selectedAbility = KeyInputHandler.getCurrentAbility();
            int useDuration = this.getUseDuration(stack) - timeLeft;

            if (selectedAbility == 0 && ManaOverlay.currentMana >= MANA_COST_METEOR) {
                if (level instanceof ServerLevel serverLevel) {
                    Vec3 startPos = player.getEyePosition();
                    Vec3 lookVector = player.getLookAngle();

                    Meteorite meteorite = new Meteorite(serverLevel, player);
                    meteorite.setPos(startPos.x, startPos.y, startPos.z);
                    meteorite.shoot(lookVector.x, lookVector.y, lookVector.z, 1.5f, 0);
                    serverLevel.addFreshEntity(meteorite);

                    ManaOverlay.currentMana -= MANA_COST_METEOR;
                    player.getCooldowns().addCooldown(this, 100);
                }
            } else if (selectedAbility == 1 && useDuration > 10 && ManaOverlay.currentMana >= MANA_COST_KNIFE) {
                if (level instanceof ServerLevel serverLevel) {
                    Vec3 startPos = player.getEyePosition();
                    Vec3 lookVector = player.getLookAngle();

                    Knife knifeProjectile = new Knife(serverLevel, player);
                    knifeProjectile.setPos(startPos.x, startPos.y, startPos.z);
                    knifeProjectile.shoot(lookVector.x, lookVector.y, lookVector.z, 2.5f, 0);
                    serverLevel.addFreshEntity(knifeProjectile);

                    ManaOverlay.currentMana -= MANA_COST_KNIFE;
                    player.getCooldowns().addCooldown(this, 20);
                }
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) { return UseAnim.BOW; }

    @Override
    public int getUseDuration(ItemStack stack) { return 72000; }
}
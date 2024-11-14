package org.zeith.modid.custom.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.client.mana.ManaOverlay;
import org.zeith.modid.custom.entyties.Knife;
import org.zeith.modid.custom.entyties.Meteorite;

public class Astral_Scepter extends Item {
    public static int currentAbility = 0;

    public Astral_Scepter(Properties properties) { super(properties); }

    public void switchAbility() { currentAbility = (currentAbility + 1) % 2; }

    public int getCurrentAbility() { return currentAbility; }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (currentAbility == 0 && ManaOverlay.currentMana >= 50) {
                if (level instanceof ServerLevel serverLevel) {
                    Vec3 startPos = player.getEyePosition();
                    Vec3 lookVector = player.getLookAngle();

                    Meteorite meteorite = new Meteorite(serverLevel, player, 5);
                    meteorite.setPos(startPos.x, startPos.y, startPos.z);
                    meteorite.shoot(lookVector.x, lookVector.y, lookVector.z, 1.5f, 0);
                    serverLevel.addFreshEntity(meteorite);

                    ManaOverlay.currentMana -= 50;
                    player.getCooldowns().addCooldown(this, 100);
                }
            } else if (currentAbility == 1 && ManaOverlay.currentMana >= 20) {
                if (level instanceof ServerLevel serverLevel) {
                    Vec3 startPos = player.getEyePosition();
                    Vec3 lookVector = player.getLookAngle();

                    Knife knifeProjectile = new Knife(serverLevel, player);
                    knifeProjectile.setPos(startPos.x, startPos.y, startPos.z);
                    knifeProjectile.shoot(lookVector.x, lookVector.y, lookVector.z, 2.5f, 0);
                    serverLevel.addFreshEntity(knifeProjectile);

                    ManaOverlay.currentMana -= 20;
                    player.getCooldowns().addCooldown(this, 100);
                }
            }
        }
        return InteractionResultHolder.success(stack);
    }
}
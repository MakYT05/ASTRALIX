package org.zeith.modid.client.ability;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.modid.custom.items.Astral_Scepter;
public class KeyInputHandler {
    @SubscribeEvent
    public static void onPlayerAttack(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.getEntity().getMainHandItem().getItem() instanceof Astral_Scepter scepter) {
            scepter.switchAbility();
        }
    }
}
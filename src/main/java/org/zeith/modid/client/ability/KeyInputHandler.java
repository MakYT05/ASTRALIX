package org.zeith.modid.client.ability;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.modid.custom.items.Astral_Scepter;

public class KeyInputHandler {
    private static int currentAbility = 0;  // Индекс текущей способности (0 = метеорит)

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        if (player.isShiftKeyDown() && player.getMainHandItem().getItem() instanceof Astral_Scepter) {
            currentAbility = (currentAbility + 1) % 1; // Пока только одна способность
            event.setCanceled(true);  // Отменяем стандартное поведение ПКМ при переключении способности
        }
    }

    public static int getCurrentAbility() { return currentAbility; }
}
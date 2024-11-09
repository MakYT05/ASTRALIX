package org.zeith.modid.client.ability;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zeith.modid.custom.items.Astral_Scepter;

@Mod.EventBusSubscriber(modid = "modid", value = Dist.CLIENT)
public class KeyInputHandler {
    public static int currentAbility = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player != null && mc.options.keyShift.isDown()) {
            if (mc.player.getMainHandItem().getItem() instanceof Astral_Scepter) {
                currentAbility = (currentAbility + 1) % 2;

                event.setCanceled(true);
            }
        }
    }

    public static int getCurrentAbility() { return currentAbility; }
}
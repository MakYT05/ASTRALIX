package org.zeith.modid.client.mana;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.modid.capability.ManaCapabilityHandler;

public class RenderManaOverlay {
    private static final int MANA_BAR_WIDTH = 100;
    private static final int MANA_BAR_HEIGHT = 10;

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent event) {
        Minecraft mc = Minecraft.getInstance();
        GuiGraphics guiGraphics = event.getGuiGraphics();

        mc.player.getCapability(ManaCapabilityHandler.MANA).ifPresent(mana -> {
            int currentMana = mana.getMana();
            int maxMana = mana.getMaxMana();
            int manaWidth = (int) ((double) currentMana / maxMana * MANA_BAR_WIDTH);

            int x = event.getWindow().getGuiScaledWidth() - MANA_BAR_WIDTH - 10;
            int y = 10;

            RenderSystem.setShaderColor(0f, 0f, 0.5f, 0.7f);
            guiGraphics.fill(x, y, x + MANA_BAR_WIDTH, y + y + MANA_BAR_HEIGHT, 0xFF202020);

            RenderSystem.setShaderColor(0.5f, 0f, 0.8f, 1f);
            guiGraphics.fill(x, y, x + manaWidth, y + MANA_BAR_HEIGHT, 0xFF5A2D82);
        });
    }
}
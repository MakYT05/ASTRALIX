package org.zeith.modid.client.mana;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "modid", value = Dist.CLIENT)
public class ManaOverlay {

    private ManaOverlay() {}

    public static int MAX_MANA = 100;
    public static int currentMana = 100;
    private static final int MANA_BAR_WIDTH = 61;
    private static final int MANA_BAR_HEIGHT = 7;
    private static final int MANA_REGEN_RATE = 1;
    private static final int TICKS_PER_REGEN = 40;
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) return;

        int scaledWidth = minecraft.getWindow().getGuiScaledWidth();
        int x = scaledWidth - 140;
        int y = 10;

        var guiGraphics = event.getGuiGraphics();

        int outlineColor = 0xFF4B0082;
        guiGraphics.fill(x - 1, y - 1, x + MANA_BAR_WIDTH + 1, y + MANA_BAR_HEIGHT + 1, outlineColor);

        guiGraphics.fill(x, y, x + MANA_BAR_WIDTH, y + MANA_BAR_HEIGHT, 0xFF000000);

        int manaWidth = (int) ((float) currentMana / MAX_MANA * MANA_BAR_WIDTH);
        guiGraphics.fill(x, y, x + manaWidth, y + MANA_BAR_HEIGHT, 0xFF00BFFF);

        Font font = minecraft.font;
        String manaText = currentMana + " / " + MAX_MANA;
        int textColor = 0xFF00BFFF;
        guiGraphics.drawString(font, manaText, x + MANA_BAR_WIDTH + 5, y + 1, textColor);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getInstance().player != null) {
            tickCounter++;

            if (tickCounter >= TICKS_PER_REGEN) {
                if (currentMana < MAX_MANA) {
                    currentMana = Math.min(currentMana + MANA_REGEN_RATE, MAX_MANA);
                }
                tickCounter = 0;
            }
        }
    }
}
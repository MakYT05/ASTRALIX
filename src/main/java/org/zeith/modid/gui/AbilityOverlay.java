package org.zeith.modid.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zeith.modid.custom.items.Astral_Scepter;

@Mod.EventBusSubscriber(modid = "modid", value = Dist.CLIENT)
public class AbilityOverlay {
    private static final ResourceLocation ICONS_METEORITE = new ResourceLocation("modid", "textures/gui/meteorite.png");
    private static final ResourceLocation ICONS_KNIFE = new ResourceLocation("modid", "textures/gui/knife.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (Astral_Scepter.currentAbility == 0) {
            if (mc.player != null && mc.player.getMainHandItem().getItem() instanceof Astral_Scepter scepter) {
                int x = mc.getWindow().getGuiScaledWidth() - 30;
                int y = mc.getWindow().getGuiScaledHeight() - 30;
                int abilityIndex = scepter.getCurrentAbility();

                RenderSystem.setShaderTexture(0, ICONS_METEORITE);
                event.getGuiGraphics().blit(ICONS_METEORITE, x, y, abilityIndex * 16, 0, 16, 16, 64, 64);
            }
        } else if (Astral_Scepter.currentAbility == 1) {
            if (mc.player != null && mc.player.getMainHandItem().getItem() instanceof Astral_Scepter scepter) {
                int x = mc.getWindow().getGuiScaledWidth() - 30;
                int y = mc.getWindow().getGuiScaledHeight() - 30;
                int abilityIndex = scepter.getCurrentAbility();

                RenderSystem.setShaderTexture(0, ICONS_KNIFE);
                event.getGuiGraphics().blit(ICONS_KNIFE, x, y, abilityIndex * 16, 0, 16, 16, 64, 64);
            }
        }
    }
}
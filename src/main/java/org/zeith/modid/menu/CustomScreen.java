package org.zeith.modid.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CustomScreen extends AbstractContainerScreen<CustomMenuContainer> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");

    public CustomScreen(CustomMenuContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, Component.literal("Усилитель знаний"));
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.fill(this.leftPos, this.topPos, this.leftPos + this.imageWidth, this.topPos + this.imageHeight, 0xFF5A2D82);

        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        guiGraphics.blit(GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title.getString(), this.titleLabelX, this.titleLabelY, 0xFFFF00FF);
    }

    //@Override
    //public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
    //    this.renderBg(guiGraphics, partialTicks, mouseX, mouseY);
    //    super.render(guiGraphics, mouseX, mouseY, partialTicks);
    //    this.renderLabels(guiGraphics, mouseX, mouseY);
    //}
}
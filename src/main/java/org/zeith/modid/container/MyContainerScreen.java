package org.zeith.modid.container;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class MyContainerScreen extends AbstractContainerScreen<MyContainer> {

    public MyContainerScreen(MyContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        ResourceLocation backgroundTexture = new ResourceLocation("modid:textures/gui/container/my_container.png");

        guiGraphics.blit(backgroundTexture, (width - imageWidth) / 2, (height - imageHeight) / 2, 0, 0, imageWidth, imageHeight);

        for (Slot slot : menu.slots) {
            renderSlot(guiGraphics, slot);
        }
    }

    protected void renderSlot(GuiGraphics guiGraphics, Slot slot) {
        int x = slot.x;
        int y = slot.y;
        guiGraphics.fill(x, y, 16, 16, 0xFF000000);
    }
}
package org.zeith.modid.container;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.zeith.modid.custom.items.AstralAmulet;

public class MyContainerScreen extends AbstractContainerScreen<MyContainer> {

    private static final ResourceLocation INVENTORY_TEXTURE = new ResourceLocation("minecraft:textures/gui/container/inventory.png");

    public MyContainerScreen(MyContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(INVENTORY_TEXTURE, (width - imageWidth) / 2, (height - imageHeight) / 2, 0, 0, imageWidth, imageHeight);

        for (Slot slot : menu.slots) {
            renderSlot(guiGraphics, slot);
        }
    }

    protected void renderSlot(GuiGraphics guiGraphics, Slot slot) {
        int x = slot.x + leftPos;
        int y = slot.y + topPos;

        if (slot.getItem().getItem() instanceof AstralAmulet) {
            guiGraphics.fill(x, y, 16, 16, 0xFF00FF00);
        } else {
            guiGraphics.blit(INVENTORY_TEXTURE, x, y, 7 * 18, 0, 18, 18);
        }
    }
}
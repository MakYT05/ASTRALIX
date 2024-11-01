package org.zeith.modid.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.zeith.modid.custom.items.AstralAmulet;
import org.zeith.modid.menu.CustomMenuTypes;

public class MyContainer extends AbstractContainerMenu {
    public MyContainer(int id, Inventory playerInventory) {
        super(CustomMenuTypes.MY_CONTAINER.get(), id);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        addSlot(new Slot(playerInventory, 0, 80, 40) {
            @Override
            public boolean mayPlace(ItemStack stack) { return stack.getItem() instanceof AstralAmulet; }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) { return ItemStack.EMPTY; }

    @Override
    public boolean stillValid(Player player) { return true; }
}
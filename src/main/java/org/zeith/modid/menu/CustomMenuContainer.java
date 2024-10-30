package org.zeith.modid.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.zeith.modid.recipe.CustomRecipeRegistry;

public class CustomMenuContainer extends AbstractContainerMenu {

    private final IItemHandler itemHandler;

    public CustomMenuContainer(int id, Inventory inventory) {
        super(CustomMenuTypes.CUSTOM_MENU.get(), id);
        this.itemHandler = new ItemStackHandler(3);

        this.addSlot(new SlotItemHandler(itemHandler, 0, 56, 17));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 56, 53));
        this.addSlot(new SlotItemHandler(itemHandler, 2, 116, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();

        ItemStack input1 = itemHandler.getStackInSlot(0);
        ItemStack input2 = itemHandler.getStackInSlot(1);
        ItemStack result = CustomRecipeRegistry.getCraftingResult(input1, input2);

        if (!result.isEmpty()) {
            itemHandler.insertItem(2, result.copy(), false);
        } else {
            itemHandler.extractItem(2, itemHandler.getStackInSlot(2).getCount(), false);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
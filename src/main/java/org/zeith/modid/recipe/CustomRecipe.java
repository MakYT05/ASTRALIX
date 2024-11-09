package org.zeith.modid.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CustomRecipe {
    private final Item input1;
    private final Item input2;
    private final ItemStack result;

    public CustomRecipe(Item input1, Item input2, ItemStack result) {
        this.input1 = input1;
        this.input2 = input2;
        this.result = result;
    }

    public Item getInput1() { return input1; }

    public Item getInput2() { return input2; }

    public ItemStack getResult() { return result; }

    public boolean matches(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == input1 && stack2.getItem() == input2;
    }
}
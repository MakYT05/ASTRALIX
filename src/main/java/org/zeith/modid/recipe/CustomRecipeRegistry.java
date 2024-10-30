package org.zeith.modid.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class CustomRecipeRegistry {
    private static final List<CustomRecipe> recipes = new ArrayList<>();

    public static void addRecipe(Item input1, Item input2, ItemStack result) {
        recipes.add(new CustomRecipe(input1, input2, result));
    }

    public static ItemStack getCraftingResult(ItemStack stack1, ItemStack stack2) {
        for (CustomRecipe recipe : recipes) {
            if (recipe.matches(stack1, stack2)) {
                return recipe.getResult();
            }
        }
        return ItemStack.EMPTY;
    }

    public static void registerRecipes() {
        addRecipe(Items.DIAMOND, Items.COAL, new ItemStack(Items.DIAMOND_SWORD));
    }
}
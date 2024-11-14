package org.zeith.modid.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.zeith.modid.custom.entyties.Meteorite;
import org.zeith.modid.init.ItemsMI;

import java.util.ArrayList;
import java.util.List;

public class CustomRecipeRegistry {
    private static final List<CustomRecipe> recipes = new ArrayList<>();

    public static void addRecipe(Item input1, Item input2, ItemStack result) {
        recipes.add(new CustomRecipe(input1, input2, result));
    }

    public static ItemStack craft(ItemStack stack1, ItemStack stack2) {
        for (CustomRecipe recipe : recipes) {
            if (recipe.matches(stack1, stack2)) {
                ItemStack result = recipe.getResult().copy();

                stack1.shrink(1);
                stack2.shrink(1);

                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    public static void registerRecipes() {
        Meteorite.radius = 10;

        ItemStack meteoriteWithRadius = new ItemStack(ItemsMI.ASTRAL_SCEPTER);
        addRecipe(ItemsMI.ASTRAL_SCEPTER, ItemsMI.ASTRALSCRIPT, meteoriteWithRadius);
    }
}

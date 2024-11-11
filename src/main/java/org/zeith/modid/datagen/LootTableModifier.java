package org.zeith.modid.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class LootTableModifier {
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation fortressChest = new ResourceLocation("minecraft", "chests/nether_fortress");

        if (event.getName().equals(fortressChest)) {
            LootTable table = event.getTable();

            ResourceLocation astralBlockId = new ResourceLocation("modid", "astral_block");

            if (ForgeRegistries.ITEMS.containsKey(astralBlockId)) {
                var astralBlockItem = ForgeRegistries.ITEMS.getValue(astralBlockId);

                if (astralBlockItem != null) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .name("astralix_in_fortress")
                            .setRolls(ConstantValue.exactly(4))
                            .add(LootItem.lootTableItem(astralBlockItem));

                    table.addPool(poolBuilder.build());
                }
            }
        }
    }
}
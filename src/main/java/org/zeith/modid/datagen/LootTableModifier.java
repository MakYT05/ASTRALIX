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
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .name("astralix_in_fortress")
                    .setRolls(ConstantValue.exactly(4))
                    .add(LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation("modid", "astral_block"))));

            table.addPool(poolBuilder.build());
        }
    }
}
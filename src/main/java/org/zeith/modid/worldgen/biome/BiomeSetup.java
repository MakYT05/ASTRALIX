package org.zeith.modid.worldgen.biome;


import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class BiomeSetup {
    @SubscribeEvent
    public static void setupBiomes(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CustomBiome.CUSTOM_BIOME_KEY, 10));
        });
    }
}
package org.zeith.modid.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.zeith.modid.init.EntitiesMI;

public class CustomBiome {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, "modid");
    public static final ResourceKey<Biome> CUSTOM_BIOME_KEY = ResourceKey.create(Registries.BIOME, new ResourceLocation("modid", "custom_biome"));

    public static final RegistryObject<Biome> CUSTOM_BIOME = BIOMES.register("custom_biome", CustomBiome::createCustomBiome);

    public CustomBiome() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BIOMES.register(modEventBus);
    }

    public static Biome createCustomBiome() {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC0D8FF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .skyColor(0x77ADFF)
                .build();

        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntitiesMI.ZEITH_MOB, 100, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntitiesMI.MOONTLEX_MOB, 100, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntitiesMI.ASTRAL_ZOMBIE, 100, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntitiesMI.RUMARUKA_MOB, 100, 4, 4));

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder((HolderGetter<PlacedFeature>) Registries.CONFIGURED_FEATURE,
                (HolderGetter<ConfiguredWorldCarver<?>>) Registries.PLACED_FEATURE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects(effects)
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
package org.zeith.modid.menu;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CustomMenuTypes {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "modid");

    public static final RegistryObject<MenuType<CustomMenuContainer>> CUSTOM_MENU = CONTAINER_TYPES.register("custom_menu",
            () -> new MenuType<>(CustomMenuContainer::new, FeatureFlags.VANILLA_SET));
}
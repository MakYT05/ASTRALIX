package org.zeith.modid;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.proxy.HLConstants;
import org.zeith.modid.Enchant.EnchantmentAstralscript;
import org.zeith.modid.client.ability.KeyInputHandler;
import org.zeith.modid.client.render.ModEntityRenderers;
import org.zeith.modid.container.MyContainerScreen;
import org.zeith.modid.custom.entyties.AstralZombieMod;
import org.zeith.modid.custom.entyties.MoontlexMob;
import org.zeith.modid.custom.entyties.RumarukaMob;
import org.zeith.modid.custom.entyties.ZeithMob;
import org.zeith.modid.datagen.LootTableModifier;
import org.zeith.modid.datagen.ModWorldGenProvider;
import org.zeith.modid.init.ItemsMI;
import org.zeith.modid.menu.CustomMenuTypes;
import org.zeith.modid.menu.CustomScreen;
import org.zeith.modid.recipe.CustomRecipeRegistry;

import java.util.concurrent.CompletableFuture;

@Mod(Astralix.MOD_ID)
public class Astralix {
	public static final String MOD_ID = "modid";

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
	public static final RegistryObject<Enchantment> ASTRALSCRIPT_ENCHANTMENT = ENCHANTMENTS.register("astralscript", EnchantmentAstralscript::new);

	@CreativeTab.RegisterTab
	public static final CreativeTab MOD_TAB = new CreativeTab(id("astralix"),
			builder -> builder
					.icon(ItemsMI.ASTRALCOIN::getDefaultInstance)
					.withTabsBefore(HLConstants.HL_TAB.id())
	);

	public Astralix() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		LanguageAdapter.registerMod(MOD_ID);

		bus.addListener(ZeithMob::entityAttributes);
		bus.addListener(MoontlexMob::entityAttributes);
		bus.addListener(AstralZombieMod::entityAttributes);
		bus.addListener(RumarukaMob::entityAttributes);

		bus.addListener(Astralix::clientSetup);
		bus.addListener(this::gatherData);

		ENCHANTMENTS.register(bus);

		//bus.addListener(EnchantListener::onLivingEntityDeath);

		CustomMenuTypes.CONTAINER_TYPES.register(bus);
	}

	private static void clientSetup(final FMLClientSetupEvent event) {
		ModEntityRenderers.registerRenderers();

		MinecraftForge.EVENT_BUS.addListener(LootTableModifier::onLootTableLoad);

		MenuScreens.register(CustomMenuTypes.CUSTOM_MENU.get(), CustomScreen::new);
		MenuScreens.register(CustomMenuTypes.MY_CONTAINER.get(), MyContainerScreen::new);

		CustomRecipeRegistry.registerRecipes();

		MinecraftForge.EVENT_BUS.register(KeyInputHandler.class);
	}

	private void gatherData(GatherDataEvent event) {
		PackOutput packOutput = event.getGenerator().getPackOutput();
		CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();
		event.getGenerator().addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, future));
	}

	public static ResourceLocation id(String path) { return new ResourceLocation(MOD_ID, path); }
}
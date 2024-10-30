package org.zeith.modid;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.proxy.HLConstants;
import org.zeith.modid.capability.ManaCapabilityHandler;
import org.zeith.modid.capability.ManaProvider;
import org.zeith.modid.capability.PlayerManaProvider;
import org.zeith.modid.client.mana.RenderManaOverlay;
import org.zeith.modid.client.render.ModEntityRenderers;
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
public class Astralix
{
	public static final String MOD_ID = "modid";

	@CreativeTab.RegisterTab
	public static final CreativeTab MOD_TAB = new CreativeTab(id("astralix"),
			builder -> builder
					.icon(() -> ItemsMI.ASTRALCOIN.getDefaultInstance())
					.withTabsBefore(HLConstants.HL_TAB.id())
	);

	public Astralix()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		LanguageAdapter.registerMod(MOD_ID);

		bus.addListener(ZeithMob::entityAttributes);
		bus.addListener(MoontlexMob::entityAttributes);
		bus.addListener(AstralZombieMod::entityAttributes);
		bus.addListener(RumarukaMob::entityAttributes);

		bus.addListener(Astralix::clientSetup);
		bus.addListener(this::gatherData);
		bus.addListener(this::registerCapabilities);
		bus.addListener(this::doClientStuff);

		CustomMenuTypes.CONTAINER_TYPES.register(bus);

		MinecraftForge.EVENT_BUS.register(new RenderManaOverlay());
	}

	private void registerCapabilities(RegisterCapabilitiesEvent event) { event.register(ManaProvider.class); }

	private void doClientStuff(final FMLClientSetupEvent event) { MinecraftForge.EVENT_BUS.register(new RenderManaOverlay());}

	private static void clientSetup(final FMLClientSetupEvent event) {
		ModEntityRenderers.registerRenderers();

		MinecraftForge.EVENT_BUS.addListener(LootTableModifier::onLootTableLoad);

		MenuScreens.register(CustomMenuTypes.CUSTOM_MENU.get(), CustomScreen::new);

		CustomRecipeRegistry.registerRecipes();
	}

	private void gatherData(GatherDataEvent event)
	{
		PackOutput packOutput = event.getGenerator().getPackOutput();
		CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();
		event.getGenerator().addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, future));
	}

	public static ResourceLocation id(String path) { return new ResourceLocation(MOD_ID, path); }

	public static class ForgeEvents {
		@SubscribeEvent
		public static void attachPlayerCapabilities(AttachCapabilitiesEvent<Player> event) {
			event.addCapability(new ResourceLocation("modid", "mana"), new PlayerManaProvider());
		}

		@SubscribeEvent
		public static void playerClone(PlayerEvent.Clone event) {
			event.getOriginal().getCapability(ManaCapabilityHandler.MANA).ifPresent(oldMana -> {
				event.getEntity().getCapability(ManaCapabilityHandler.MANA).ifPresent(newMana -> {
					newMana.setMana(oldMana.getMana());
					newMana.setMaxMana(oldMana.getMaxMana());
				});
			});
		}
	}
}
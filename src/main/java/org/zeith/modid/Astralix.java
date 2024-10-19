package org.zeith.modid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.core.init.ItemsHL;
import org.zeith.hammerlib.proxy.HLConstants;
import org.zeith.modid.client.ModEntityRenderers;
import org.zeith.modid.datagen.LootTableModifier;
import org.zeith.modid.custom.entyties.ZeithMob;

@Mod(Astralix.MOD_ID)
public class Astralix
{
	public static final String MOD_ID = "modid";

	@CreativeTab.RegisterTab
	public static final CreativeTab MOD_TAB = new CreativeTab(id("astralix"),
			builder -> builder
					.icon(() -> ItemsHL.COPPER_GEAR.getDefaultInstance())
					.withTabsBefore(HLConstants.HL_TAB.id())
	);

	public Astralix()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		LanguageAdapter.registerMod(MOD_ID);

		bus.addListener(Astralix::clientSetup);
		bus.addListener(ZeithMob::entityAttributes);
	}

	private static void clientSetup(final FMLClientSetupEvent event) {
		ModEntityRenderers.registerRenderers();
		MinecraftForge.EVENT_BUS.addListener(LootTableModifier::onLootTableLoad);
	}

	public static ResourceLocation id(String path) { return new ResourceLocation(MOD_ID, path); }
}
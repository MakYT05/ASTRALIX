package org.zeith.modid.init;

import net.minecraft.world.item.*;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.Astralix;
import org.zeith.modid.custom.items.*;

import static org.zeith.modid.init.EntitiesMI.ZEITH_MOB;

@SimplyRegister
public interface ItemsMI{
	@RegistryName("lightning_wand")
	Item LIGHTNING_WAND = Astralix.MOD_TAB.add(new LightningWandItem(new Item.Properties().durability(10).rarity(Rarity.EPIC)));

	@RegistryName("ender_sword")
	Item ENDER_SWORD = Astralix.MOD_TAB.add(new EnderSwordItem(Tiers.DIAMOND, 10, 2F, new Item.Properties().durability(2000).rarity(Rarity.EPIC)));

	@RegistryName("fire_axe")
	Item FIRE_AXE = Astralix.MOD_TAB.add(new FireAxeItem(Tiers.DIAMOND, 6, 1F, new Item.Properties().durability(2000).rarity(Rarity.EPIC)));

	@RegistryName("zeith_egg")
	Item ZEITH_EGG = Astralix.MOD_TAB.add(new ZeithEggItem(ZEITH_MOB, new Item.Properties().durability(10).rarity(Rarity.EPIC)));

	@RegistryName("tnt_bow")
	Item TNT_BOW = Astralix.MOD_TAB.add(new TntBowItem(new Item.Properties().durability(10).rarity(Rarity.EPIC)));

	@RegistryName("dark_brade")
	Item DARK_BRAID = Astralix.MOD_TAB.add(new DarkBrade(Tiers.DIAMOND, 8, 1.5F, new Item.Properties().durability(2000).rarity(Rarity.EPIC)));

	@RegistryName("astralit")
	Item ASTRALIT = Astralix.MOD_TAB.add(new Item(new Item.Properties()));

	@RegistryName("astralcoin")
	Item ASTRALCOIN = Astralix.MOD_TAB.add(new Item(new Item.Properties()));

	@RegistryName("astral_coffee")
	Item ASTRAL_COFFEE = Astralix.MOD_TAB.add(new AstralCoffee(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));
}
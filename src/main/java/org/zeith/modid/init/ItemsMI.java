package org.zeith.modid.init;

import net.minecraft.world.item.*;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.Astralix;
import org.zeith.modid.custom.items.*;

import static org.zeith.modid.init.EntitiesMI.ZEITH_MOB;

@SimplyRegister
public interface ItemsMI {
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
	Item ASTRALIT = Astralix.MOD_TAB.add(new Item(new Item.Properties().rarity(Rarity.EPIC)));

	@RegistryName("astralcoin")
	Item ASTRALCOIN = Astralix.MOD_TAB.add(new Item(new Item.Properties().rarity(Rarity.EPIC)));

	@RegistryName("astral_coffee")
	Item ASTRAL_COFFEE = Astralix.MOD_TAB.add(new AstralCoffee(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

	@RegistryName("astral_stick")
	Item ASTRAL_STICK = Astralix.MOD_TAB.add(new Item(new Item.Properties().rarity(Rarity.EPIC)));

	@RegistryName("astral_beer")
	Item ASTRAL_BEER = Astralix.MOD_TAB.add(new Astral_Beer(new Item.Properties().rarity(Rarity.EPIC)));

	@RegistryName("astral_scepter")
	Item ASTRAL_SCEPTER = Astralix.MOD_TAB.add(new Astral_Scepter(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

	@RegistryName("astral_amulet")
	Item ASTRAL_AMULET = Astralix.MOD_TAB.add(new AstralAmulet(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

	@RegistryName("magic_stick")
	Item MAGIC_STICK = Astralix.MOD_TAB.add(new MagicStick(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

	@RegistryName("astralscript")
	Item ASTRALSCRIPT = Astralix.MOD_TAB.add(new Item(new Item.Properties().rarity(Rarity.EPIC)));

	@RegistryName("astral_sword")
	Item ASTRAL_SWORD = Astralix.MOD_TAB.add(new AstralSword(Tiers.DIAMOND, 10, 1.5F, new Item.Properties().rarity(Rarity.EPIC)));

	@RegistryName("astral_spear")
	Item ASTRAL_SPEAR = Astralix.MOD_TAB.add(new AstralSpear(Tiers.DIAMOND, 10, 1F, new Item.Properties().rarity(Rarity.EPIC)));

	@RegistryName("astral_mana")
	Item ASTRAL_MANA = Astralix.MOD_TAB.add(new AstralMana(new Item.Properties().rarity(Rarity.EPIC)));
}
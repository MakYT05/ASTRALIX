package org.zeith.modid.Enchant;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class EnchantmentAstralscript extends Enchantment {

    public EnchantmentAstralscript() { super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND}); }

    @Override
    public int getMaxLevel() { return 3; }

    @Override
    public int getMinCost(int level) { return 5 + (level - 1) * 10; }

    @Override
    public int getMaxCost(int level) { return super.getMinCost(level) + 15; }
}
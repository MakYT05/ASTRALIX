package org.zeith.modid.init;

import net.minecraft.world.level.block.Block;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.Astralix;
import org.zeith.modid.custom.blocks.*;

@SimplyRegister
public interface BlocksMI {
    @RegistryName("astral_block")
    Block ASTRAL_BLOCK = Astralix.MOD_TAB.add(new AstralBlock());

    @RegistryName("astral_turf")
    Block ASTRAL_TURF = Astralix.MOD_TAB.add(new AstralTurf());

    @RegistryName("astral_tree")
    Block ASTRAL_TREE = Astralix.MOD_TAB.add(new AstralTree());

    @RegistryName("astral_leaves")
    Block ASTRAL_LEAVES = Astralix.MOD_TAB.add(new AstralLeaves());

    @RegistryName("astralit_block")
    Block ASTRALIT_BLOCK = Astralix.MOD_TAB.add(new AstralitBlock());

    @RegistryName("sapling_astral")
    Block SAPLING_ASTRAL = Astralix.MOD_TAB.add(new SaplingAstral());

    @RegistryName("astral_table")
    Block ASTRAL_TABLE = Astralix.MOD_TAB.add(new AstralTable());

    @RegistryName("astral_meh")
    Block ASTRAL_MEH = Astralix.MOD_TAB.add(new AstralMeh());

    @RegistryName("astral_way")
    Block ASTRAL_WAY = Astralix.MOD_TAB.add(new AstralWay());
}
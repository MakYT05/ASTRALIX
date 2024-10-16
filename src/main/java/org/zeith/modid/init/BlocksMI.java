package org.zeith.modid.init;

import net.minecraft.world.level.block.Block;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.Astralix;
import org.zeith.modid.custom.blocks.AstralBlock;
import org.zeith.modid.custom.blocks.AstralTree;
import org.zeith.modid.custom.blocks.AstralTurf;

@SimplyRegister
public interface BlocksMI
{
    @RegistryName("astral_block")
    Block ASTRAL_BLOCK = Astralix.MOD_TAB.add(new AstralBlock());

    @RegistryName("astral_turf")
    Block ASTRAL_TURF = Astralix.MOD_TAB.add(new AstralTurf());

    @RegistryName("astral_tree")
    Block ASTRAL_TREE = Astralix.MOD_TAB.add(new AstralTree());
}
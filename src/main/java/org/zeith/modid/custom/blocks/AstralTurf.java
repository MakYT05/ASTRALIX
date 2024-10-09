package org.zeith.modid.custom.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AstralTurf extends Block {
    public AstralTurf()
    {
        super(BlockBehaviour.Properties.of()
                .strength(3.0F, 10.0F)
                .sound(SoundType.GRAVEL)
                .requiresCorrectToolForDrops());
    }
}
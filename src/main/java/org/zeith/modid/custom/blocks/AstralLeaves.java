package org.zeith.modid.custom.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AstralLeaves extends Block {
    public AstralLeaves()
    {
        super(BlockBehaviour.Properties.of()
                .strength(1.0F, 1.0F)
                .sound(SoundType.GRAVEL)
                .requiresCorrectToolForDrops());
    }
}
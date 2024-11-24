package org.zeith.modid.custom.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SaplingAstral extends Block {
    public SaplingAstral() {
        super(BlockBehaviour.Properties.of()
                .strength(10.0F, 8.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops());
    }
}
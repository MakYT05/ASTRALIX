package org.zeith.modid.custom.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AstralTree extends Block {
    public AstralTree() {
        super(BlockBehaviour.Properties.of()
                .strength(10.0F, 8.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops());
    }
}
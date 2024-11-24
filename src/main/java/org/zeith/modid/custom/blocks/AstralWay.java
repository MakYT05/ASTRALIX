package org.zeith.modid.custom.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AstralWay extends Block {
    public AstralWay() {
        super(BlockBehaviour.Properties.of()
                .strength(3.0F, 10.0F)
                .sound(SoundType.GRAVEL)
                .requiresCorrectToolForDrops());
    }

    @Override
    public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }
}
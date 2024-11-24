package org.zeith.modid.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zeith.modid.client.mana.ManaOverlay;

import java.util.List;

public class AstralMeh extends Block {

    private static final double REGEN_RADIUS = 10.0;
    private static final int MANA_REGEN_AMOUNT = 5;
    private static final int TICKS_PER_REGEN = 40;
    private static int tickCounter = 0;

    public AstralMeh() {
        super(BlockBehaviour.Properties.of()
                .strength(10.0F, 8.0F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
    }

    @Mod.EventBusSubscriber(modid = "modid", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ManaRegenHandler {

        @SubscribeEvent
        public static void onServerTick(TickEvent.ServerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;

            tickCounter++;

            if (tickCounter < TICKS_PER_REGEN) return;

            tickCounter = 0;

            var server = net.minecraftforge.server.ServerLifecycleHooks.getCurrentServer();

            if (server != null) {
                for (ServerLevel world : server.getAllLevels()) {
                    for (ServerPlayer player : world.players()) {
                        BlockPos playerPos = player.blockPosition();
                        AABB searchBox = new AABB(playerPos).inflate(REGEN_RADIUS);

                        List<BlockPos> blocksNearby = BlockPos.betweenClosedStream(searchBox)
                                .filter(pos -> world.getBlockState(pos).getBlock() instanceof AstralMeh)
                                .toList();

                        if (!blocksNearby.isEmpty()) {
                            ManaOverlay.currentMana = Math.min(
                                    ManaOverlay.currentMana + MANA_REGEN_AMOUNT,
                                    ManaOverlay.MAX_MANA
                            );
                        }
                    }
                }
            }
        }
    }
}
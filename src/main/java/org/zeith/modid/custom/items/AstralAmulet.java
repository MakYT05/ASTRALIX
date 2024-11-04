package org.zeith.modid.custom.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AstralAmulet extends Item {

    public AstralAmulet(Properties properties) { super(properties); }

    @SubscribeEvent
    public void inventoryTick(TickEvent.PlayerTickEvent event, Level world, Player player, int slotId, boolean isSelected, Level level) {
        if (!level.isClientSide) {
            for (ItemStack stack : player.getInventory().items) {
                if (stack.getItem() instanceof AstralAmulet) {
                    ((AstralAmulet) stack.getItem()).inventoryTick(stack, level, player, -1, false);
                }
            }
        }
    }
}
package org.zeith.modid.custom.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.zeith.modid.client.mana.ManaOverlay;

public class AstralAmulet extends Item {

    public AstralAmulet(Properties properties) { super(properties); }

    public void inventoryTick(ItemStack stack, Level world, Player player, int slotId, boolean isSelected) {
        if (!world.isClientSide) {
            ManaOverlay.MAX_MANA = 200;
        }
    }
}
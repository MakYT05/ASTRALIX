package org.zeith.modid.capability;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerManaProvider implements ICapabilityProvider {
    private final Mana mana = new Mana();
    private final LazyOptional<IMana> manaOptional = LazyOptional.of(() -> mana);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ManaCapabilityHandler.MANA ? manaOptional.cast() : LazyOptional.empty();
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Player> event) {
        event.addCapability(new ResourceLocation("modid", "mana"), new PlayerManaProvider());
    }
}
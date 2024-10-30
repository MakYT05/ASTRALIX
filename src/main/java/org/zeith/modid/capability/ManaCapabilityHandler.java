package org.zeith.modid.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ManaCapabilityHandler {
    public static final Capability<IMana> MANA = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IMana.class);
    }

    public static void attachCapabilities(AttachCapabilitiesEvent<Player> event) {
        event.addCapability(new ResourceLocation("modid", "mana"), new ManaProvider());
    }

    public static void playerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(MANA).ifPresent(oldMana -> {
            event.getEntity().getCapability(MANA).ifPresent(newMana -> {
                newMana.setMana(oldMana.getMana());
                newMana.setMaxMana(oldMana.getMaxMana());
            });
        });
    }
}
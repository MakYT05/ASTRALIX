package org.zeith.modid.capability;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ManaProvider implements ICapabilityProvider {
    private final ManaCapability mana = new ManaCapability();
    private final LazyOptional<IMana> manaCapability = LazyOptional.of(() -> mana);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ManaCapabilityHandler.MANA ? manaCapability.cast() : LazyOptional.empty();
    }
}
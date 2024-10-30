package org.zeith.modid.capability;

public class ManaCapability implements IMana {
    private int mana;
    private int maxMana;

    public ManaCapability() {
        this.mana = 0;
        this.maxMana = 100;
    }

    @Override
    public int getMana() { return mana; }

    @Override
    public int getMaxMana() { return maxMana; }

    @Override
    public void setMana(int mana) { this.mana = mana; }

    @Override
    public void setMaxMana(int maxMana) { this.maxMana = maxMana; }
}
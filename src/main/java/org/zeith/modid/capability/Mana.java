package org.zeith.modid.capability;

public class Mana implements IMana {
    private int mana;
    private final int maxMana = 100;

    public Mana() {
        this.mana = 0;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return 0;
    }

    @Override
    public void setMana(int mana) {
        this.mana = Math.min(mana, maxMana);
    }

    @Override
    public void setMaxMana(int maxMana) {

    }
}
package com.codecool.dungeoncrawl.logic.items;

public class Shield extends Items{

    private int durability;
    private int shieldEffect;


    public void increaseDurability() {
        setDurability(getDurability() + 2);
    }

    public void decreaseDurability(){
        setDurability(getDurability() - 1);
    }
}

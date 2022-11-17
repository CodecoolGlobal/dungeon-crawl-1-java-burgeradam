package com.codecool.dungeoncrawl.logic.items;

public class sword extends Items {


    public void decreaseDurability() {
        setDurability(getDurability() - 1);
    }

    public void increaseDurability(){
        setDurability(getDurability() + 1);
    }
}

package com.codecool.dungeoncrawl.logic.items;

public class Key extends Items{

    private boolean alreadyUsed = false;

    public boolean isKeyUsed(){
        return alreadyUsed;
    }

    public void useKey() {
        alreadyUsed = true;
    }

}

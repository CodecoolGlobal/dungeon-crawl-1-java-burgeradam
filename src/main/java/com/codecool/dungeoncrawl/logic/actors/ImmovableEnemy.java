package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class ImmovableEnemy extends Actor {
    public ImmovableEnemy(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "immovable";
    }
}

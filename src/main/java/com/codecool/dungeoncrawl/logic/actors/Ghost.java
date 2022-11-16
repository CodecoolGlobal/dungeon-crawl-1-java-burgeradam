package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {

    public Ghost(Cell cell) {
        super(cell);
        this.setHealth(2);
        this.setDefense(1);
        this.setAttack(3);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}

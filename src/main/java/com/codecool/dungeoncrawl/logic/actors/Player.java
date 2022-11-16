package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        this.setHealth(10);
        this.setAttack(1);
        this.setDefense(1);
    }

    public String getTileName() {
        return "player";
    }
}

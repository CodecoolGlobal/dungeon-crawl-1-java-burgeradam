package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private static int health = 10;

    private int attack;

    private int defense;

    private boolean isDead;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
        this.isDead = false;
    }
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public static int getHealth() {
        return health;
    }



    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isAllowedOnTile(Cell cell) {
        return !cell.getType().equals(CellType.WALL) &&
                !cell.getType().equals(CellType.EMPTY) &&
                !cell.getType().equals(CellType.WATER) &&
                !cell.getType().equals(CellType.TREE) &&
                !cell.getType().equals(CellType.DOOR) &&
                !(cell.getActor() instanceof Skeleton) &&
                !(cell.getActor() instanceof Ghost) &&
                !(cell.getActor() instanceof Player);
    }
}

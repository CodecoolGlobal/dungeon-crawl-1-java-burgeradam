package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.ImmovableEnemy;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isOnAllowedTile(Cell cell) {
        return !cell.getType().equals(CellType.WALL) &&
                !cell.getType().equals(CellType.EMPTY) &&
                !(cell.getActor() instanceof Skeleton) &&
                !(cell.getActor() instanceof ImmovableEnemy);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

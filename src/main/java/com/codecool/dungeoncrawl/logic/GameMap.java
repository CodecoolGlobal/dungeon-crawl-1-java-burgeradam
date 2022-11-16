package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
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
        return !cell.getType().equals(CellType.WALL) && !cell.getType().equals(CellType.TREE) &&
                !cell.getType().equals(CellType.EMPTY) && !cell.getType().equals(CellType.WATER) &&
                !(cell.getActor() instanceof Skeleton) && !(cell.getActor() instanceof Ghost);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isEnemy(Cell nextCell) {
        return nextCell.getType().equals(CellType.GHOST) || nextCell.getType().equals(CellType.SKELETON);
    }
}

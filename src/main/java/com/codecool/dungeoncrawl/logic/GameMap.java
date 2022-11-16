package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private List<Skeleton> skeletons = new ArrayList<>();

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

    public void addSkeleton(Skeleton skeleton) {
        skeletons.add(skeleton);
    }

    public boolean isAllowedOnTile(Cell cell) {
        return !cell.getType().equals(CellType.WALL) &&
                !cell.getType().equals(CellType.EMPTY) &&
                !cell.getType().equals(CellType.WATER) &&
                !cell.getType().equals(CellType.TREE) &&
                !cell.getType().equals(CellType.DOOR) &&
                !(cell.getActor() instanceof Skeleton &&
                !(cell.getActor() instanceof Ghost));
    }

    public void moveSkeleton() {
        int minValue = -1;
        int maxValue = 1;
        for (Skeleton skeleton : skeletons) {
            Coordinate coordinate = new Coordinate();
            double moveDirection = Math.floor(Math.random() * (maxValue - minValue + 1) + minValue);
            coordinate.setX((int) moveDirection);
            moveDirection = Math.floor(Math.random() * (maxValue - minValue + 1) + minValue);
            coordinate.setY((int) moveDirection);
            Cell skeletonCell = skeleton.getCell();
            Cell nextSkeletonCell = skeletonCell.getNeighbor(coordinate.getX(), coordinate.getY());
            if (isAllowedOnTile(nextSkeletonCell)) {
                skeletonCell.setType(CellType.FLOOR);
                skeleton.move(coordinate.getX(), coordinate.getY());
            }
        }
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

    public boolean isDoor(Cell nextCell) {
        return nextCell.getType().equals(CellType.DOOR);
    }

    public String fight(Cell cell) {
        String name = cell.getType().name();
        System.out.println(name);
        return name;
    }
}

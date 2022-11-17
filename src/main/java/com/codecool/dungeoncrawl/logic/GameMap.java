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
    private List<Ghost> ghosts = new ArrayList<>();

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

    public void addGhost(Ghost ghost) {
        ghosts.add(ghost);
    }

    public void moveSkeleton() {
        for (Skeleton skeleton : skeletons) {
            Coordinate coordinate = new Coordinate();
            coordinate.setX((int) getMoveDirection());
            coordinate.setY((int) getMoveDirection());
            Cell skeletonCell = skeleton.getCell();
            Cell nextSkeletonCell = skeletonCell.getNeighbor(coordinate.getX(), coordinate.getY());
            if (skeleton.isAllowedOnTile(nextSkeletonCell)) {
                skeletonCell.setType(CellType.FLOOR);
                skeleton.move(coordinate.getX(), coordinate.getY());
            }
        }
    }

    public void moveGhost() {
        for (Ghost ghost : ghosts) {
            Coordinate coordinate = new Coordinate();
            coordinate.setX((int) getMoveDirection());
            coordinate.setY((int) getMoveDirection());
            Cell ghostCell = ghost.getCell();
            Cell nextGhostCell = ghostCell.getNeighbor(coordinate.getX(), coordinate.getY());
            if (ghost.isAllowedOnTile(nextGhostCell)) {
                ghostCell.setType(CellType.FLOOR);
                ghost.move(coordinate.getX(), coordinate.getY());
            }
        }
    }

    private static double getMoveDirection() {
        int minValue = -1;
        int maxValue = 1;
        return Math.floor(Math.random() * (maxValue - minValue + 1) + minValue);
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

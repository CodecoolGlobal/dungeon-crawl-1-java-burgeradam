package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
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

    public void enemyMove(Class clazz) {
        List<? extends Actor> enemyList = null;
        if (clazz.equals(Skeleton.class)) {
            enemyList = skeletons;
        } else if (clazz.equals(Ghost.class)){
            enemyList = ghosts;
        } else {
            System.out.println("not valid class for movement");
        }
        if (enemyList != null) {
            Actor selectedEnemy = null;
            for (Actor enemy : enemyList){
                if (!enemy.isDead()) {
                    Coordinate coordinate = new Coordinate();
                    coordinate.setX((int) getMoveDirection());
                    coordinate.setY((int) getMoveDirection());
                    Cell enemyCell = enemy.getCell();
                    Cell nextEnemyCell = enemyCell.getNeighbor(coordinate.getX(), coordinate.getY());
                    if (nextEnemyCell.isAllowedOn() && nextEnemyCell.getType() != CellType.KEY ) {
                        nextEnemyCell.setType(enemyCell.getType());
                        enemyCell.setType(CellType.FLOOR);
                        enemy.move(coordinate.getX(), coordinate.getY());
                    }
                } else {
                    enemy.getCell().setActor(null);
                    selectedEnemy = enemy;
                    enemyList.remove(selectedEnemy);
                    break;
                }
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

    public Actor getEnemy(Cell nextCell) {
        return nextCell.getActor();
    }

    public boolean isDoor(Cell nextCell) {
        return nextCell.getType().equals(CellType.DOOR);
    }

    public void fight(Cell enemyPosition, Cell playerPosition) {
        Actor enemy = enemyPosition.getActor();
        Actor player = playerPosition.getActor();
        int enemyHealth = enemy.getHealth();
        int enemyAttack = enemyPosition.getActor().getAttack();
        int enemyDefense = enemyPosition.getActor().getDefense();

        int playerHealth = player.getHealth();
        int playerAttack = playerPosition.getActor().getAttack();
        int playerDefense = playerPosition.getActor().getDefense();

        int playerPossibleDamage = playerAttack - enemyDefense;
        if (enemyHealth > 0) {
            enemyPosition.getActor().setHealth(enemyHealth - playerPossibleDamage);
        } else {
            enemyPosition.getActor().setDead(true);
            enemyPosition.setType(CellType.FLOOR);
        }

        System.out.println("Enemy Stats:" + enemyHealth +" "+ enemyAttack +" "+ enemyDefense);
        System.out.println("Player Stats:" + playerHealth +" "+ playerAttack +" "+ playerDefense);
        System.out.println(enemyPosition.getType());
    }
}

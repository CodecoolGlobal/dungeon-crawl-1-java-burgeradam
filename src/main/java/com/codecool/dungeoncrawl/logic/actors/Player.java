package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Player extends Actor {
    private static int uiCounter = 2;

    private static ArrayList<Items> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        this.setHealth(10);
        this.setAttack(2);
        this.setDefense(1);
    }

    public static ArrayList<Items> getInventory() {
        return inventory;
    }


    public static void pickUp(Cell cell, GridPane ui) {

        if (cell.getTileName().equals("sword")) {
            Items sword = new sword();
            inventory.add(sword);
            cell.setType(CellType.FLOOR);
            ui.add(new Label("sword"), 0, uiCounter);
            uiCounter++;

        } else if (cell.getTileName().equals("shield")) {
            Items shield = new Shield();
            inventory.add(shield);
            cell.setType(CellType.FLOOR);
            ui.add(new Label("shield"), 0, uiCounter);
            uiCounter++;

        } else if (cell.getTileName().equals("key")) {

            Items key = new Key();
            inventory.add(key);
            cell.setType(CellType.FLOOR);
            ui.add(new Label("key"), 0, uiCounter);
            uiCounter++;

        } else if(cell.getTileName().equals("heal1")) {

            Healer heal1 = new Healer("heal1");

        }


    }

    public Key getKey(){
        Key out = null;
        for (Items item: inventory){
            if (item instanceof Key) out = (Key)item;
        }
        return out;
    }

        @Override
        public String getTileName() {
            return "player";
        }

    }

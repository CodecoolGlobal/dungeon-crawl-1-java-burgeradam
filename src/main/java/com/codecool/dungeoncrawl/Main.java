package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    GridPane ui = new GridPane();
    GameMap map = MapLoader.loadMap();
    private class SkeletonThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                map.moveSkeleton();
                map.moveGhost();
                refresh();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label key = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.setPrefWidth(100);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Inventory:"), 0,1);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        SkeletonThread skeletonThread = new SkeletonThread();
        skeletonThread.start();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Player playerPlace = map.getPlayer();
        Cell playerActualCell = playerPlace.getCell();
        int moveToX = 0;
        int moveToY = 0;
        switch (keyEvent.getCode()) {
            case UP:
                moveToX = 0;
                moveToY = -1;
                Player.pickUp(playerActualCell, ui);
                break;
            case DOWN:
                Player.pickUp(playerActualCell, ui);
                moveToX = 0;
                moveToY = 1;
                break;
            case LEFT:
                Player.pickUp(playerActualCell, ui);
                moveToX = -1;
                moveToY = 0;
                break;
            case RIGHT:
                Player.pickUp(playerActualCell, ui);
                moveToX = 1;
                moveToY = 0;
                break;

        }

        Cell nextCell = playerActualCell.getNeighbor(moveToX, moveToY);
        if (playerPlace.isAllowedOnTile(nextCell)) {
            map.getPlayer().move(moveToX, moveToY);
        } else {
            if (map.isEnemy(nextCell)) {
                map.fight(nextCell, playerActualCell);
            }
            if (map.isDoor(nextCell)) {
/*
                Stage messageWindow = new Stage();

                GridPane ui = new GridPane();
                ui.setPrefWidth(200);
                ui.setPadding(new Insets(10));
                ui.add(new Label("Health: "), 0, 0);

                BorderPane bPane = new BorderPane();
                bPane.setCenter(canvas);
                bPane.setRight(ui);

                Scene scene = new Scene(bPane);
                messageWindow.setScene(scene);
                refresh();
                scene.setOnKeyPressed(this::onKeyPressed);

                messageWindow.setTitle("Hello there!");
                messageWindow.show();
ű
*/
                map = MapLoader.loadMap();
            }
        }
        refresh();
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}

package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(18, 8));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("sword", new Tile(0,29));
        tileMap.put("heal1", new Tile(15,28));
        tileMap.put("heal2", new Tile(16,28));
        tileMap.put("heal3", new Tile(18,28));
        tileMap.put("tree", new Tile(0,1));
        tileMap.put("water", new Tile(8,5));
        tileMap.put("shield", new Tile(7,24));
        tileMap.put("door", new Tile(10,9));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}

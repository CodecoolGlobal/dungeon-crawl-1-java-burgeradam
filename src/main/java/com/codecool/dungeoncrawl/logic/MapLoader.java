package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class MapLoader {

    static File topLeftMap = new File ("src/main/resources.txt"); // 0
    static File topRightMap = new File ("src/main/resources.txt"); // 1
    static File bottomRightMap = new File ("src/main/resources.txt"); // 2
    static File bottomLeftMap = new File ("src/main/resources.txt"); // 3
    static HashMap<Integer, mapPart> mapPosition;
    static mapPart playersMap;
    static mapPart doorMap;
    static int mapRowsCount = 26;   // height
    static int mapColCount = 42;    // width
    static int mapVariationsCount = 3;

    public static GameMap loadMap() {
        mapPosition.put(0, mapPart.topLeft);
        mapPosition.put(1, mapPart.topRight);
        mapPosition.put(2, mapPart.bottomRight);
        mapPosition.put(3, mapPart.bottomLeft);
/*
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine(); // empty line
*/
        String[] mapStringVector = mapFilesRandomReader();

        int width = mapColCount;
        int height = mapRowsCount;
        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = mapStringVector[y];
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'q':
                            cell.setType(CellType.SWORD);
                            break;
                        case 'b':
                            cell.setType(CellType.HEAL1);
                            break;
                        case 'c':
                            cell.setType(CellType.HEAL2);
                            break;
                        case 'm':
                            cell.setType(CellType.HEAL3);
                            break;
                        case 'r':
                            cell.setType(CellType.SHIELD);
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    static String[] mapFilesRandomReader() {
        Random random = new Random();
        playersMap = mapPosition.get( random.nextInt(3) );
        doorMap = mapPosition.get( random.nextInt(3) );

        String[][] mapStringMatrix = new String[4][mapRowsCount];
        String fileName = "";
        for (int position: mapPosition.keySet()) {
            if (playersMap == mapPosition.get(position)) fileName = mapPosition.get(position).getFilePrefix() + "p";
            if    (doorMap == mapPosition.get(position)) fileName = mapPosition.get(position).getFilePrefix() + "d";
            else fileName = mapPosition.get(position).getFilePrefix() + random.nextInt(mapVariationsCount);
            Scanner fileReader = new Scanner(fileName);
            int i = 0;
            while (fileReader.hasNext()){
                mapStringMatrix[position][i] = fileReader.nextLine();
                i++;
            }
            fileReader.close();
        }

        String[] mapStringVector = new String[mapRowsCount];
        for (int i=0; i < mapRowsCount/2; i++) {
            mapStringVector[i] = mapStringMatrix[0][i];
            mapStringVector[i] += mapStringMatrix[1][i];
            mapStringVector[i+mapRowsCount/2] += mapStringMatrix[2][i+mapRowsCount/2];
            mapStringVector[i+mapRowsCount/2] += mapStringMatrix[3][i+mapRowsCount/2];
        }
        return mapStringVector;
    }
}

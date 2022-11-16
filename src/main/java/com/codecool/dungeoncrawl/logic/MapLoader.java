package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.util.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class MapLoader {
    static HashMap<Integer, mapPart> mapPosition = new HashMap<>();
    static mapPart playersMap = null;
    static mapPart doorMap = null;
    static int mapRowCount = 26;   // height
    static int mapColCount = 42;    // width
    static int mapVariationsCount = 2;
    static int halfWidth;
    static int halfHeight;
    static HashMap<Character, CellType> charToCellType;

    static {
        mapPosition.put(0, mapPart.topLeft);
        mapPosition.put(1, mapPart.topRight);
        mapPosition.put(2, mapPart.bottomLeft);
        mapPosition.put(3, mapPart.bottomRight);

        halfWidth = mapColCount / 2;
        halfHeight = mapRowCount /2;

        charToCellType = new HashMap<>();
        charToCellType.put(' ', CellType.EMPTY);
        charToCellType.put('#', CellType.WALL);
        charToCellType.put('.', CellType.FLOOR);
        charToCellType.put('k', CellType.KEY);
        charToCellType.put('s', CellType.FLOOR);
        charToCellType.put('q', CellType.SWORD);
        charToCellType.put('b', CellType.HEAL1);
        charToCellType.put('c', CellType.HEAL2);
        charToCellType.put('m', CellType.HEAL3);
        charToCellType.put('r', CellType.SHIELD);
        charToCellType.put('t', CellType.TREE);
        charToCellType.put('w', CellType.WATER);
        charToCellType.put('d', CellType.DOOR);
        charToCellType.put('g', CellType.WATER); // This is temponary!!!
        charToCellType.put('@', CellType.FLOOR);
    }

    public static GameMap loadMap() {
/*
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine(); // empty line
*/
        String[] mapStringVector = mapFilesRandomReader();

        int width = mapColCount;
        int height = mapRowCount;
        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = mapStringVector[y];
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    cell.setType( charToCellType.get(line.charAt(x)) );
                    switch (line.charAt(x)){
                        case 's':
                            new Skeleton(cell);
                            break;
                        case '@':
                            map.setPlayer(new Player(cell));
                            break;
                    }
                    if (charToCellType.get(line.charAt(x)) == null) throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");


/*
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
                        case 'g':  // ez csak ideiglenes!!!
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
*/
                }
            }
        }
        return map;
    }

    static String[] mapFilesRandomReader() {
        Random random = new Random();
        playersMap = mapPosition.get( random.nextInt(3) );
        doorMap = mapPosition.get( random.nextInt(3) );
        while (doorMap == playersMap) doorMap = mapPosition.get( random.nextInt(3) );

        String[][] mapStringMatrix = new String[4][mapRowCount];
        String fileName = "";
        for (int position: mapPosition.keySet()) {
            if (playersMap == mapPosition.get(position)) fileName = mapPosition.get(position).getFilePrefix() + "p" + ".txt";
            // if (doorMap == mapPosition.get(position)) fileName = mapPosition.get(position).getFilePrefix() + "d" + ".txt";
             else fileName = mapPosition.get(position).getFilePrefix() + (random.nextInt(mapVariationsCount) + 1) + ".txt";
            // else fileName = mapPosition.get(position).getFilePrefix() + "1" + ".txt";

            try {
                File fileObject = new File(fileName);
                Scanner fileReader = new Scanner(fileObject);
                int i = 0;
                while (fileReader.hasNext()) {
                    String str = fileReader.nextLine();
                    int strWidth = str.length();
                    if (str.length() < halfWidth) {
                        str = Utils.addSpacesToString(str, halfWidth);
                    }
                    mapStringMatrix[position][i] = str;
                    i++;
                }
                fileReader.close();
            }
            catch (FileNotFoundException e){
                throw new RuntimeException(e.getMessage());
            }
        }


        String[] mapStringVector = new String[mapRowCount];
        for (int i = 0; i < mapRowCount /2; i++) {
            mapStringVector[i] = mapStringMatrix[0][i];
            mapStringVector[i] += mapStringMatrix[1][i];
            mapStringVector[i+ mapRowCount /2] = mapStringMatrix[2][i];
            mapStringVector[i+ mapRowCount /2] += mapStringMatrix[3][i];
        }
        return mapStringVector;
    }

}

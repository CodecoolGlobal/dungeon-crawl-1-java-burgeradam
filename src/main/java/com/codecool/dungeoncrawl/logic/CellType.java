package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    HEAL1("heal1"),
    HEAL2("heal2"),
    TREE("tree"),
    WATER("water"),
    DOOR("door"),
    HEAL3("heal3"),
    SWORD("sword"),
    KEY("key"),
    SHIELD("shield"),
    WALL("wall"),
    GHOST("ghost"),
    SKELETON("skeleton");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

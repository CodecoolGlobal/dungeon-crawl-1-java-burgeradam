package com.codecool.dungeoncrawl.logic;

public enum mapPart {
    topLeft ("src/main/resources/map_tl_"),
    topRight ("src/main/resources/map_tr_"),
    bottomLeft ("src/main/resources/map_bl_"),
    bottomRight ("src/main/resources/map_br_");

    public String getFilePrefix() {
        return filePrefix;
    }

    String filePrefix;

    mapPart(String filePrefix){
        this.filePrefix = filePrefix;
    }
}

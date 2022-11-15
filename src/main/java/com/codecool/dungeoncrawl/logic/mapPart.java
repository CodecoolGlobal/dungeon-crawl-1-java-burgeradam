package com.codecool.dungeoncrawl.logic;

public enum mapPart {
    topLeft ("map_tl_"),
    topRight ("map_tr_"),
    bottomRight ("map_br_"),
    bottomLeft ("map_bl_");

    public String getFilePrefix() {
        return filePrefix;
    }

    String filePrefix;

    mapPart(String filePrefix){
        this.filePrefix = filePrefix;
    }



}

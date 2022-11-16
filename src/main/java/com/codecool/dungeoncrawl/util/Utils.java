package com.codecool.dungeoncrawl.util;

public class Utils {
    public static String addSpacesToString(String str, int lengthTo){
        String spaces = "";
        if (str.length()>=lengthTo) return str;
        else {
            int diffLength = lengthTo - str.length();
            for (int i=0; i<diffLength; i++){
                spaces += " ";
            }
        }
        return str+spaces;
    }
}

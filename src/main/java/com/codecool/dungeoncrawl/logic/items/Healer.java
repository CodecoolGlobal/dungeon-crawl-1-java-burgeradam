package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Healer extends Items{

    private String healType;

    public Healer(String healType){
        this.healType = healType;
    }

    public String getHealType(){
        return this.healType;
    }


    public int heal(int life) {
        if (healType.equals("heal1")){
            if (life + 1 < 10){
               return life + 1;
            }else {
                life = 10;
                return life;
            }
        }
        if(healType.equals("heal2")){
            if(life + 3 < 10){
                return life + 3;
            } else {
                life = 10;
                return life;
            }
        }
        if(healType.equals("heal3")){

            return 10;
        }
        return life;
    }
}

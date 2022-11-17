package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Healer extends Items{

    private String healType;

    public Healer(String healType){
        this.healType = healType;
    }

    public String getHealType(){
        return this.healType;
    }


    public void heal(int life, Player player) {
        if (healType.equals("heal1")){
            if (life + 1 < 10){
               player.setHealth(life + 2);
            }else {
                life = 10;
                player.setHealth(life);
            }
        }
        if(healType.equals("heal2")){
            if(life + 3 < 10){
                player.setHealth(life + 3);
            } else {
                life = 10;
                player.setHealth(life);
            }
        }
        if(healType.equals("heal3")){

            player.setHealth(10);
        }
    }
}

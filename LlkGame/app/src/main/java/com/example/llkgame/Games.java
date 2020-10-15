package com.example.llkgame;

import java.io.Serializable;

public class Games implements Serializable {
    public String gameName;
    public int gameImageId;
    public Games(){}

    public Games(String gameName,int gameImageId){
        this.gameName = gameName;
        this.gameImageId = gameImageId;
    }
    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName){
        this.gameName = gameName;
    }
    public int getGameImageId(){ return gameImageId; }
    public void setGameImageId(int gameImageId){
        this.gameImageId = gameImageId;
    }
}

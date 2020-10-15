package com.example.llkgame;

import java.io.Serializable;

public class Head implements Serializable {
    public String gameName;
    public int headImageId;
    public Head(){}

    public Head(int gameImageId){
        this.headImageId = gameImageId;
    }
    public int getHeadImageId(){ return headImageId; }
    public void setHeadImageId(int gameImageId){
        this.headImageId = gameImageId;
    }
}
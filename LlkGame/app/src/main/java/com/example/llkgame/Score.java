package com.example.llkgame;

public class Score {
    private String score;
    private int scoreId;
    private int scoreImage;
    private String userName;
    public  Score(){}

    public Score(String score,int scoreId,String userName,int scoreImage) {
        this.score = score;
        this.scoreId =scoreId;
        this.userName = userName;
        this.scoreImage = scoreImage;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public int getScoreImage() { return scoreImage; }

    public void setScoreImage(int scoreImage) { this.scoreImage = scoreImage; }

    public int getScoreId() {
        return scoreId;
    }

    public String getScore() {
        return score;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

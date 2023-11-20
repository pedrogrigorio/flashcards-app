package com.example.flashcards_app.models;

public class Review {

    String frontText;
    String backText;
    int id;
    Integer stampLevel = null;

    public Review() {

    }

    public Review(String frontText, String backText, int id, Integer stampLevel) {
        this.frontText = frontText;
        this.backText = backText;
        this.id = id;
        this.stampLevel = stampLevel;
    }

    public Integer getStampLevel() {
        return stampLevel;
    }

    public void setStampLevel(Integer stampLevel) {
        this.stampLevel = stampLevel;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


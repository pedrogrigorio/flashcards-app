package com.example.flashcards_app.models;

public class NewCards {

    String frontText;
    String backText;
    int  id;

    public NewCards(String frontText, String backText, int id) {
        this.frontText = frontText;
        this.backText = backText;
        this.id = id;
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

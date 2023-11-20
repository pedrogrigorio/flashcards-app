package com.example.flashcards_app.models;

public class NewCards {

    String frontText;
    String backText;
    int  id;
    int assignmentLevel = -1;

    public NewCards(String frontText, String backText, int id, int assignmentLevel) {
        this.frontText = frontText;
        this.backText = backText;
        this.id = id;
        this.assignmentLevel = assignmentLevel;
    }

    public int getAssignmentLevel() {
        return assignmentLevel;
    }

    public void setAssignmentLevel(int assignmentLevel) {
        this.assignmentLevel = assignmentLevel;
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

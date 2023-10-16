package com.example.flashcards_app.models;

public class User {

    int id;

    String name;
    public User(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

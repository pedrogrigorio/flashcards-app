package com.example.flashcards_app.models;

import java.util.Vector;

public class CardScheme {
    private int id;
    private String front;
    private String back;

    public CardScheme(int id, String front, String back) {
        this.id = id;
        this.front = front;
        this.back = back;
    }

    public int getId() {
        return id;
    }
    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nFront: " + front + "\nBack: " + back + "\n";
    }

}

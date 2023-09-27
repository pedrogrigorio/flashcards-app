package com.example.flashcards_app.models;

import java.util.Vector;

public class CardDataSchemeControl {
    private int id;
    private String front;
    private String back;
    static Vector<Integer> traceBackId;

    public CardDataSchemeControl(int id, String front, String back) {
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

    public boolean checkTraceBack(int id) {
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nFront: " + front + "\nBack: " + back + "\n";
    }

}

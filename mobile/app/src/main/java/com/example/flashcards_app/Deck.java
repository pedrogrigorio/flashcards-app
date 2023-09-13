package com.example.flashcards_app;

import android.view.View;
import android.widget.TextView;

public class Deck {
    private int id;
    private String title;
    private TextView titleTextView;

    public Deck(int id, String title, TextView titleTextView) {
        this.id = id;
        this.title = title;
        this.titleTextView = titleTextView;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
        titleTextView.setText(title);
    }
}

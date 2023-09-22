package com.example.flashcards_app.models;

import android.widget.TextView;

public class Friend {

    int id;
    String name;
    TextView nameView;

    public Friend(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextView getNameView() {
        return nameView;
    }

    public void setNameView(TextView nameView) {
        this.nameView = nameView;
    }
}

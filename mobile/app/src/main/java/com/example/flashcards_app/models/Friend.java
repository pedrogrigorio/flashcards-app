package com.example.flashcards_app.models;

import android.widget.TextView;

public class Friend {

    int id;
    String name;
    String username;
    String imgSrc;

    // Tmp constructor
    public Friend(int id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.imgSrc = "https://cdn.vectorstock.com/i/preview-1x/08/19/gray-photo-placeholder-icon-design-ui-vector-35850819.jpg";
    }

    public Friend(int id, String name, String username, String imgSrc) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.imgSrc = imgSrc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

}

package com.example.flashcards_app.models;

public class User {

    String name;
    String username;
    String imgSrc;
    int id;
    int dayStreak;
    int cardsReviewed;
    boolean isFriend;

    public User(String name, String username) {
        this.name = name;
        this.username = username;
        this.imgSrc = "https://cdn.vectorstock.com/i/preview-1x/08/19/gray-photo-placeholder-icon-design-ui-vector-35850819.jpg";
        this.dayStreak = 0;
        this.id = 0;
        this.cardsReviewed = 0;
        this.isFriend = true;
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

    public int getDayStreak() {
        return dayStreak;
    }

    public void setDayStreak(int dayStreak) {
        this.dayStreak = dayStreak;
    }

    public int getCardsReviewed() {
        return cardsReviewed;
    }

    public void setCardsReviewed(int cardsReviewed) {
        this.cardsReviewed = cardsReviewed;
    }

    public boolean getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

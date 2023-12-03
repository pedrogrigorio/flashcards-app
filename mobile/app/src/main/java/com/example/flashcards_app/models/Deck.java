package com.example.flashcards_app.models;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

public class Deck {

    private int id;
    private String imgSrc;
    private String title;
    private int newCardsCount;
    private int learnCardCount;
    private int reviewCardsCount;

    public Deck() {
        this.id = 0;
        this.imgSrc = "https://www.haliburtonforest.com/wp-content/uploads/2017/08/placeholder-square.jpg";
        this.title = "Novo baralho";
        this.newCardsCount = 0;
        this.learnCardCount = 0;
        this.reviewCardsCount = 0;
    }

    public Deck(int id, String imgSrc, String title, int newCardsNumber, int learnCardsNumber, int reviewCardsNumber) {
        this.id = id;
        this.imgSrc = imgSrc;
        this.title = title;
        this.newCardsCount = newCardsNumber;
        this.learnCardCount = learnCardsNumber;
        this.reviewCardsCount = reviewCardsNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNewCardsNumber() {
        return newCardsCount;
    }

    public void setNewCardsNumber(int newCardsNumber) {
        this.newCardsCount = newCardsNumber;
    }

    public int getLearnCardsNumber() {
        return learnCardCount;
    }

    public void setLearnCardsNumber(int learnCardsNumber) {
        this.learnCardCount = learnCardsNumber;
    }

    public int getReviewCardsNumber() {
        return reviewCardsCount;
    }

    public void setReviewCardsNumber(int reviewCardsNumber) {
        this.reviewCardsCount = reviewCardsNumber;
    }
}

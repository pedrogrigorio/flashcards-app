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
    private int newCardsNumber;
    private int learnCardsNumber;
    private int reviewCardsNumber;

    public Deck() {
        this.imgSrc = "https://www.haliburtonforest.com/wp-content/uploads/2017/08/placeholder-square.jpg";
        this.title = "Novo baralho";
        this.newCardsNumber = 0;
        this.learnCardsNumber = 0;
        this.reviewCardsNumber = 0;
    }

    public Deck(String imgSrc, String title, int newCardsNumber, int learnCardsNumber, int reviewCardsNumber) {
        this.imgSrc = imgSrc;
        this.title = title;
        this.newCardsNumber = newCardsNumber;
        this.learnCardsNumber = learnCardsNumber;
        this.reviewCardsNumber = reviewCardsNumber;
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
        return newCardsNumber;
    }

    public void setNewCardsNumber(int newCardsNumber) {
        this.newCardsNumber = newCardsNumber;
    }

    public int getLearnCardsNumber() {
        return learnCardsNumber;
    }

    public void setLearnCardsNumber(int learnCardsNumber) {
        this.learnCardsNumber = learnCardsNumber;
    }

    public int getReviewCardsNumber() {
        return reviewCardsNumber;
    }

    public void setReviewCardsNumber(int reviewCardsNumber) {
        this.reviewCardsNumber = reviewCardsNumber;
    }
}

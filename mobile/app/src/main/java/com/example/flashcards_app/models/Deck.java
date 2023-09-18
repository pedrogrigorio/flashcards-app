package com.example.flashcards_app.models;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

public class Deck {
    private int id;
    private String title;
    private int newCardsNumber;
    private int learnCardsNumber;
    private int reviewCardsNumber;

    private ImageView deckImage;

    private TextView titleTextView;
    private TextView newCardsNumberTextView;
    private TextView learnCardsNumberTextView;
    private TextView reviewCardsNumberTextView;

    public Deck(int id) {
        this.id = id;
        this.title = "Novo baralho";
        this.newCardsNumber = 0;
        this.learnCardsNumber = 0;
        this.reviewCardsNumber = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateTitle();
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

    // Image getters and setters

    public ImageView getDeckImage() {
        return deckImage;
    }

    public void setDeckImage(ImageView deckImage) {
        this.deckImage = deckImage;
    }

    // TextViews Setters

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
        updateTitle();
    }
    public void setNewCardsNumberTextView(TextView newCardsNumberTextView) {
        this.newCardsNumberTextView = newCardsNumberTextView;
    }

    public void setLearnCardsNumberTextView(TextView learnCardsNumberTextView) {
        this.learnCardsNumberTextView = learnCardsNumberTextView;
    }

    public void setReviewCardsNumberTextView(TextView reviewCardsNumberTextView) {
        this.reviewCardsNumberTextView = reviewCardsNumberTextView;
    }

    // Private methods
    private void updateTitle() {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }
}

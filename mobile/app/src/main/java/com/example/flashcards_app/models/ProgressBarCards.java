package com.example.flashcards_app.models;

import android.view.View;
import android.widget.TextView;
import android.widget.ProgressBar;


public class ProgressBarCards {

    private final TextView progressText;
    private final ProgressBar progressBar;
    private int amount = 0;
    private int current = 0;

    public ProgressBarCards(TextView progressText, ProgressBar progressBar) {
        this.progressText = progressText;
        this.progressBar  = progressBar;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.updateProgressBar();
    }

    public void setCurrent(int current) {
        this.current = current;
        this.updateProgressBar();
    }

    private void setProgressText() {
        this.progressText.setText(String.format(this.current + "/" + this.amount));
    }
    private void updateProgressBar() {
        this.progressBar.setProgress((int) ((float)this.current / this.amount * 100));
        this.setProgressText();
    }

}

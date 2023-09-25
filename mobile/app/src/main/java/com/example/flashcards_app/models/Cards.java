package com.example.flashcards_app.models;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards_app.R;
import androidx.appcompat.app.AppCompatActivity;


public class Cards extends AppCompatActivity  {

    private TextView frontCardText;
    private TextView backCardText;
    private Button easyButton;
    private Button goodButton;
    private Button hardButton;
    public int amount;
    public int current;
    private AnimatorSet frontAnim;
    private AnimatorSet backAnim;
    private float scale;
    private boolean isFront;
    private View frontCardViewText;
    private View backCardViewText;
    private Context context;


    public Cards(Context context,
                 View frontCardViewText,
                 View backCardViewText,
                 TextView frontCardText,
                 TextView backCardText,
                 Button easyButton,
                 Button goodButton,
                 Button hardButton,
                 AnimatorSet frontAnim,
                 AnimatorSet backAnim) {

        this.frontCardViewText = frontCardViewText;
        this.backCardViewText  = backCardViewText;
        this.frontCardText     = frontCardText;
        this.backCardText      = backCardText;
        this.easyButton        = easyButton;
        this.goodButton        = goodButton;
        this.hardButton        = hardButton;
        this.frontAnim         = frontAnim;
        this.backAnim          = backAnim;
        this.scale             = context.getApplicationContext().getResources().getDisplayMetrics().density;
        this.isFront           = true;
        this.setCameraCardDistance();
    }


    private void difficultButton() {
        this.easyButton.setOnClickListener(v -> {
            this.makeAnimation();
        });
        this.goodButton.setOnClickListener(v -> {
            this.makeAnimation();
        });
        this.hardButton.setOnClickListener(v -> {
            this.makeAnimation();
        });

    }
    private void setCameraCardDistance() {
        this.frontCardViewText.setCameraDistance(8000*this.scale);
        this.backCardViewText.setCameraDistance(8000*this.scale);
    }
    public void setFrontCardText(String frontCardText) {
        this.frontCardText.setText(frontCardText);
    }

    public void setBackCardText(String backCardText) {
        this.backCardText.setText(backCardText);
    }

    private void setCurrentValue(int current) {
        this.current = current;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void makeAnimation() {
        if (this.isFront) {
            this.frontAnim.setTarget(this.frontCardViewText);
            this.backAnim.setTarget(this.backCardViewText);
            frontAnim.start();
            backAnim.start();
            isFront = false;
        } else {
            this.frontAnim.setTarget(this.backCardViewText);
            this.backAnim.setTarget(this.frontCardViewText);
            backAnim.start();
            frontAnim.start();
            isFront = true;
        }
    }

}
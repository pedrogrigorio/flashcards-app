package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Cards;


public class ReviewActivity extends AppCompatActivity {

    Cards card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        this.card = new Cards(this ,findViewById(R.id.frontCardViewText),
                findViewById(R.id.backCardViewText),
                findViewById(R.id.frontCardText),
                findViewById(R.id.backCardText),
                findViewById(R.id.easy_button),
                findViewById(R.id.good_button),
                findViewById(R.id.hard_button),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator));


        Button microphoneButton = findViewById(R.id.microphone_button);
        microphoneButton.setOnClickListener(v -> {
            audioTextSpeaker();
        });


    }


    // Method for listening native speaker audio
    public void audioTextSpeaker() {
        this.card.makeAnimation();
    }



}
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

    private AnimatorSet frontAnim;

    private AnimatorSet backAnim;

    private Boolean isFront = true;

    private Float scale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Cards card = new Cards();

        Button microphoneButton = findViewById(R.id.microphone_button);
        microphoneButton.setOnClickListener(v -> {
            card.makeAnimation();
        });


    }


    // Method for listening native speaker audio
    public void audioTextSpeaker() {

    }



//    // Method for user speaker text in your microphone
//    public void microphoneUserSpeaker() {
//
//        scale = getResources().getDisplayMetrics().density;
//        View backCardViewText = findViewById(R.id.backCardViewText);
//        View frontCardViewText = findViewById(R.id.frontCardViewText);
//        backCardViewText.setCameraDistance(8000*scale);
//        frontCardViewText.setCameraDistance(8000*scale);
//
//        frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator);
//        backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator);
//
//
//        if (isFront) {
//            frontAnim.setTarget(frontCardViewText);
//            backAnim.setTarget(backCardViewText);
//            frontAnim.start();
//            backAnim.start();
//            isFront = false;
//        } else {
//            frontAnim.setTarget(backCardViewText);
//            backAnim.setTarget(frontCardViewText);
//            backAnim.start();
//            frontAnim.start();
//            isFront = true;
//        }
//    }




}
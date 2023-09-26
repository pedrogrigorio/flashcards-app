package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Cards;
import com.example.flashcards_app.models.AudioCard;


public class ReviewActivity extends AppCompatActivity {

    private Cards card;
    private AudioCard audioCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        Button microphoneButton = findViewById(R.id.microphone_button);
        Button easyButton       = findViewById(R.id.easy_button);
        Button goodButton       = findViewById(R.id.good_button);
        Button hardButton       = findViewById(R.id.hard_button);
        Button audioButton      = findViewById(R.id.audio_button);
        View leftClickableRegionFront = findViewById(R.id.leftClickableRegionFront);
        View rightClickableRegionFront = findViewById(R.id.rightClickableRegionFront);
        View leftClickableRegionBack = findViewById(R.id.leftClickableRegionBack);
        View rightClickableRegionBack = findViewById(R.id.rightClickableRegionBack);

        this.card = new Cards(this ,findViewById(R.id.frontCardViewText),
                findViewById(R.id.backCardViewText),
                findViewById(R.id.frontCardText),
                findViewById(R.id.backCardText),
                easyButton,
                goodButton,
                hardButton,
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator_anticlockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator_anticlockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator_clockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator_clockwise));
        this.card.showControlDifficultButton(false);
        this.card.setFrontCardText("Hello, this is a sample text to be spoken in English.");
        this.card.setBackCardText("Oi, este é pedaço de texto para ser lido em inglês");

        easyButton.setOnClickListener(v-> {
            this.card.easyButtonCommand();
        });

        goodButton.setOnClickListener(v-> {
            this.card.goodButtonCommand();
        });

        hardButton.setOnClickListener(v-> {
            this.card.hardButtonCommand();
        });

        audioButton.setOnClickListener(v-> {
            this.card.audioSpeak();
        });

        rightClickableRegionBack.setOnClickListener(v->{
            this.card.makeAnimationRight();

            Toast.makeText(getApplicationContext(), "rightClickableRegionBack" , Toast.LENGTH_SHORT).show();
        });

        leftClickableRegionBack.setOnClickListener(v->{
            this.card.makeAnimationLeft();

            Toast.makeText(getApplicationContext(), "leftClickableRegionBack" , Toast.LENGTH_SHORT).show();
        });

        rightClickableRegionFront.setOnClickListener(v->{
            this.card.makeAnimationRight();

            Toast.makeText(getApplicationContext(), "rightClickableRegionFront" , Toast.LENGTH_SHORT).show();

        });

        leftClickableRegionFront.setOnClickListener(v->{
            this.card.makeAnimationLeft();
            Toast.makeText(getApplicationContext(), "leftClickableRegionFront" , Toast.LENGTH_SHORT).show();

        });



    }



    @Override
    protected void onDestroy() {
        if (this.audioCard != null) {
            this.audioCard.shutDown();
        }
        super.onDestroy();
    }


}
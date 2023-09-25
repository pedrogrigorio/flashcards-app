package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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

        this.card = new Cards(this ,findViewById(R.id.frontCardViewText),
                findViewById(R.id.backCardViewText),
                findViewById(R.id.frontCardText),
                findViewById(R.id.backCardText),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator));

        card.setFrontCardText("Hello, this is a sample text to be spoken in English.");

        microphoneButton.setOnClickListener(v -> {
            audioTextSpeaker();
        });

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

    }


    // Method for listening native speaker audio
    public void audioTextSpeaker() {
        this.card.makeAnimation();
    }

    @Override
    protected void onDestroy() {
        if (this.audioCard != null) {
            this.audioCard.shutDown();
        }
        super.onDestroy();
    }


}
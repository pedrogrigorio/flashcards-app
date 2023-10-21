package com.example.flashcards_app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Animator;
import com.example.flashcards_app.models.Cards;
import com.example.flashcards_app.models.AudioCard;
import com.example.flashcards_app.models.ProgressBarCards;


public class ReviewActivity extends AppCompatActivity {

    private Cards card;
    private AudioCard audioCard;
    private Animator animator;
    private ProgressBarCards progressBarCards;
    private int count = 0;
    private int indexCardControl = 1;
    private boolean hiddenControl = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        Button microphoneButton  = findViewById(R.id.microphone_button);
        Button easyButton        = findViewById(R.id.easy_button);
        Button goodButton        = findViewById(R.id.good_button);
        Button hardButton        = findViewById(R.id.hard_button);
        Button audioButton       = findViewById(R.id.audio_button);
        View leftClickableRegionFront = findViewById(R.id.leftClickableRegionFront);
        View rightClickableRegionFront = findViewById(R.id.rightClickableRegionFront);
        View leftClickableRegionBack = findViewById(R.id.leftClickableRegionBack);
        View rightClickableRegionBack = findViewById(R.id.rightClickableRegionBack);


        this.card = new Cards(this,
                findViewById(R.id.frontCardText),
                findViewById(R.id.backCardText),
                easyButton,
                goodButton,
                hardButton,
                audioButton);


        this.animator = new Animator(this,
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator_anticlockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator_anticlockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator_clockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator_clockwise),
                findViewById(R.id.frontCardViewText),
                findViewById(R.id.backCardViewText));


        this.progressBarCards = new ProgressBarCards(findViewById(R.id.progressText), findViewById(R.id.progressBar));
        this.setProgressBarParameters();


        easyButton.setOnClickListener(v-> {
            this.updateIndexCardControl();
            if (this.card.easyButtonCommand(this.indexCardControl)) {
                this.animator.makeAnimationRight();
            }
        });

        goodButton.setOnClickListener(v-> {
            this.card.goodButtonCommand(indexCardControl);
            if (this.card.goodButtonCommand(this.indexCardControl)) {
                this.animator.makeAnimationRight();
            }
        });

        hardButton.setOnClickListener(v-> {
            this.card.goodButtonCommand(indexCardControl);
            if (this.card.hardButtonCommand(this.indexCardControl)) {
                this.animator.makeAnimationRight();
            }
        });

        audioButton.setOnClickListener(v-> {
            this.card.audioSpeak();
        });

        leftClickableRegionBack.setOnClickListener(v->{
            this.animator.makeAnimationLeft();
            this.card.showControlDifficultButton(!hiddenControl);
        });

        rightClickableRegionFront.setOnClickListener(v->{
            this.animator.makeAnimationRight();
            this.card.showControlDifficultButton(!hiddenControl);
        });

        leftClickableRegionFront.setOnClickListener(v->{

        });

        rightClickableRegionBack.setOnClickListener(v->{

        });

        microphoneButton.setOnClickListener(v -> {
            this.animator.makeAnimationRight();
            this.card.showControlDifficultButton(!hiddenControl);
        });

    }


    @Override
    protected void onDestroy() {
        if (this.audioCard != null) {
            this.audioCard.shutDown();
        }
        super.onDestroy();
    }

    private void setProgressBarParameters() {
        this.progressBarCards.setAmount(this.card.getCardAmount());
    }

    private void alertDialogEndCard() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Você finalizou seus estudos por hoje. Parabêns!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }

        });
        AlertDialog dialog = builder.create();
        dialog.create();
    }

    private void updateIndexCardControl() {
        if (indexCardControl > this.card.getCardAmount()) {
            this.alertDialogEndCard();
        } else {
            this.indexCardControl+=1;
            this.progressBarCards.setCurrent(this.indexCardControl);
        }
    }


}
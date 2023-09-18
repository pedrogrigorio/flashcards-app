package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flashcards_app.R;


public class ReviewActivity extends AppCompatActivity {

    private AnimatorSet frontAnim;

    private AnimatorSet backAnim;

    private Boolean isFront = true;

    private Float scale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

    }

    public void startAnimation() {
        scale = getResources().getDisplayMetrics().density;
        View backCardViewText = findViewById(R.id.backCardText);
        View frontCardViewText = findViewById(R.id.frontCardText);
        backCardViewText.setCameraDistance(8000*scale);
        frontCardViewText.setCameraDistance(8000*scale);

        frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator);
        backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator);

    }

    // Method for use as 'text writer' in front card
    public void writeFrontTextCardView() {
        TextView frontCardText = findViewById(R.id.frontCardText);
        frontCardText.setText("Front Card aqui");
    }

    // Method for use as 'text writer' in front card
    public void writeBackTextCardView() {
        TextView backCardText = findViewById(R.id.backCardText);
        backCardText.setText("Back Card Aqui");
    }

    // Method for listening native speaker audio
    public void audioTextSpeaker() {

    }
    // Method for user speaker text in your microphone
    public void microphoneUserSpeaker() {

        View backCardViewText = findViewById(R.id.backCardText);
        View frontCardViewText = findViewById(R.id.frontCardText);

        Button microphoneButton = findViewById(R.id.microphone_button);
        microphoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFront) {
                    frontAnim.setTarget(frontCardViewText);
                    backAnim.setTarget(backCardViewText);
                    frontAnim.start();
                    backAnim.start();
                    isFront = false;
                } else {
                    frontAnim.setTarget(backCardViewText);
                    backAnim.setTarget(frontCardViewText);
                    backAnim.start();
                    frontAnim.start();
                }
            }
        });
    }




}
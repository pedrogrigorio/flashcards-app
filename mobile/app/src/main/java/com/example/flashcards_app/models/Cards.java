package com.example.flashcards_app.models;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.flashcards_app.R;
import androidx.appcompat.app.AppCompatActivity;


public class Cards extends AppCompatActivity  {

    private TextView frontCardText;
    private TextView backCardText;
    public int amount;
    public int current;
    private AnimatorSet frontAnim;
    private AnimatorSet backAnim;
    private float scale;
    private boolean isFront;
    private View frontCardViewText;
    private View backCardViewText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.frontCardText = findViewById(R.id.frontCardText);
        this.backCardText = findViewById(R.id.backCardText);
        this.frontCardViewText = findViewById(R.id.frontCardViewText);
        this.backCardViewText = findViewById(R.id.backCardViewText);
        this.frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator);
        this.backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator);
        this.scale = getResources().getDisplayMetrics().density;
        this.setCameraCardDistance();
        this.current = 0;
        this.amount  = 0;
    }


    private void setCameraCardDistance() {
        this.frontCardViewText.setCameraDistance(this.scale * 8000);
        this.backCardViewText.setCameraDistance(this.scale * 8000);
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

/*
* Created by: Richard Lira
* Date: 08/10/2023
* Function: Class responsible for performing the animation calculations that take place on the card.
*/


package com.example.flashcards_app.models;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.ReviewActivity;

public class Animator extends ReviewActivity {

    private AnimatorSet frontAnimAntiClockWise;
    private AnimatorSet backAnimAntiClockWise;
    private AnimatorSet frontAnimClockWise;
    private AnimatorSet backAnimClockWise;
    private float scale;
    private View frontCardViewText;
    private View backCardViewText;
    private boolean turnControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Inflate complete " , Toast.LENGTH_SHORT).show();
        this.backAnimClockWise  = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator_clockwise);
        this.frontAnimClockWise = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator_clockwise);
        this.backAnimAntiClockWise  = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator_anticlockwise);
        this.frontAnimAntiClockWise = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator_clockwise);
        this.frontCardViewText = findViewById(R.id.frontCardViewText);
        this.backCardViewText  =  findViewById(R.id.backCardViewText);
        this.scale = getApplicationContext().getResources().getDisplayMetrics().density;
        this.setCameraCardDistance();
        this.turnControl = true;


    }
//    public Animator() {
//        setCameraCardDistance();
//    }
    private void setCameraCardDistance() {
        this.frontCardViewText.setCameraDistance(8000*this.scale);
        this.backCardViewText.setCameraDistance(8000*this.scale);
    }

    private void setVisibilityFrontCard() {
        this.frontCardViewText.setVisibility(View.VISIBLE);
    }
    private void setInvisibilityFrontCard() {this.frontCardViewText.setVisibility(View.INVISIBLE); }
    private void mainAnimationEngine(int animationId) {
        switch (animationId) {
            case 1:
                if (this.turnControl) {
                this.frontAnimClockWise.setTarget(this.frontCardViewText);
                this.backAnimClockWise.setTarget(this.backCardViewText);
                frontAnimClockWise.start();
                backAnimClockWise.start();
                this.turnControl = false;
                this.frontCardViewText.postDelayed(this::setInvisibilityFrontCard, 1000);
            } else {
                this.frontAnimClockWise.setTarget(this.backCardViewText);
                this.backAnimClockWise.setTarget(this.frontCardViewText);
                backAnimClockWise.start();
                frontAnimClockWise.start();
                this.turnControl = true;
                this.setVisibilityFrontCard();
            }
            break;
            case 2:
                if (this.turnControl) {
                this.frontAnimAntiClockWise.setTarget(this.frontCardViewText);
                this.backAnimAntiClockWise.setTarget(this.backCardViewText);
                frontAnimAntiClockWise.start();
                backAnimAntiClockWise.start();
                this.turnControl = false;
                this.frontCardViewText.postDelayed(this::setInvisibilityFrontCard, 1000);
            } else {
                this.frontAnimAntiClockWise.setTarget(this.backCardViewText);
                this.backAnimAntiClockWise.setTarget(this.frontCardViewText);
                backAnimAntiClockWise.start();
                frontAnimAntiClockWise.start();
                this.turnControl = true;
                this.setVisibilityFrontCard();
            }
            break;
        }
    }


    public void makeAnimationLeft() {
      this.mainAnimationEngine(1);
    }
    public void makeAnimationRight() {
        this.mainAnimationEngine(2);
    }




//    public void makeAnimationLeft() {
//        if (this.isBack) {
//            this.frontAnimClockWise.setTarget(this.frontCardViewText);
//            this.backAnimClockWise.setTarget(this.backCardViewText);
//            frontAnimClockWise.start();
//            backAnimClockWise.start();
//            this.isBack = false;
//            this.isFront = false;
//            this.frontCardViewText.postDelayed(this::setInvisibilityFrontCard, 1000);
//        } else {
//            this.frontAnimClockWise.setTarget(this.backCardViewText);
//            this.backAnimClockWise.setTarget(this.frontCardViewText);
//            backAnimClockWise.start();
//            frontAnimClockWise.start();
//            this.isBack = true;
//            this.isFront = true;
//            this.setVisibilityFrontCard();
//        }
//    }
//
//    public void makeAnimationRight() {
//        if (this.isFront) {
//            this.frontAnimAntiClockWise.setTarget(this.frontCardViewText);
//            this.backAnimAntiClockWise.setTarget(this.backCardViewText);
//            frontAnimAntiClockWise.start();
//            backAnimAntiClockWise.start();
//            this.isFront = false;
//            this.isBack  = false;
//            this.frontCardViewText.postDelayed(this::setInvisibilityFrontCard, 1000);
//            this.showControlDifficultButton(true);
//        } else {
//            this.frontAnimAntiClockWise.setTarget(this.backCardViewText);
//            this.backAnimAntiClockWise.setTarget(this.frontCardViewText);
//            backAnimAntiClockWise.start();
//            frontAnimAntiClockWise.start();
//            this.isFront = true;
//            this.isBack  = true;
//            this.showControlDifficultButton(false);
//            this.setVisibilityFrontCard();
//        }
//    }




}

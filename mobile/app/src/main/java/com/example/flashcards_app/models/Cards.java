package com.example.flashcards_app.models;


import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class Cards {

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
    private AudioCard audioCard;


    public Cards(Context context,
                 View frontCardViewText,
                 View backCardViewText,
                 TextView frontCardText,
                 TextView backCardText,
                 AnimatorSet frontAnim,
                 AnimatorSet backAnim) {

        this.frontCardViewText = frontCardViewText;
        this.backCardViewText  = backCardViewText;
        this.frontCardText     = frontCardText;
        this.backCardText      = backCardText;
        this.audioCard         =  new AudioCard(context);
        this.frontAnim         = frontAnim;
        this.backAnim          = backAnim;
        this.scale             = context.getApplicationContext().getResources().getDisplayMetrics().density;
        this.isFront           = true;
        this.setCameraCardDistance();

    }


    public void easyButtonCommand() {
        this.makeAnimationRight();
    }

    public void goodButtonCommand() {
        this.makeAnimationRight();
    }

    public void hardButtonCommand() {
        this.makeAnimationRight();
    }
    public void audioSpeak() {
        this.audioCard.speak(this.frontCardText.getText().toString());
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

    public void makeAnimationRight() {
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

    public void makeAnimationLeft() {
        if (this.isFront) {
            this.frontAnim.setTarget(this.frontCardViewText);
            this.backAnim.setTarget(this.backCardViewText);
            backAnim.start();
            frontAnim.start();
            isFront = false;
        } else {
            this.frontAnim.setTarget(this.backCardViewText);
            this.backAnim.setTarget(this.frontCardViewText);
            frontAnim.start();
            backAnim.start();
            isFront = true;
        }
    }

}

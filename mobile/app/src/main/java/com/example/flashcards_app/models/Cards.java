package com.example.flashcards_app.models;


import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Cards {

    private TextView frontCardText;
    private TextView backCardText;
    public int amount;
    public int current;
    private AnimatorSet frontAnimAntiClockWise;
    private AnimatorSet backAnimAntiClockWise;
    private AnimatorSet frontAnimClockWise;
    private AnimatorSet backAnimClockWise;
    private float scale;
    private boolean isFront;
    private boolean isBack;
    private View frontCardViewText;
    private View backCardViewText;
    private AudioCard audioCard;
    private Button easyButton;
    private Button goodButton;
    private Button hardButton;



    public Cards(Context context,
                 View frontCardViewText,
                 View backCardViewText,
                 TextView frontCardText,
                 TextView backCardText,
                 Button easyButton,
                 Button goodButton,
                 Button hardButton,
                 AnimatorSet frontAnimAntiClockWise,
                 AnimatorSet backAnimAntiClockWise,
                 AnimatorSet frontAnimClockWise,
                 AnimatorSet backAnimClockWise) {

        this.frontCardViewText = frontCardViewText;
        this.backCardViewText  = backCardViewText;
        this.frontCardText     = frontCardText;
        this.easyButton        = easyButton;
        this.goodButton        = goodButton;
        this.hardButton        = hardButton;
        this.backCardText      = backCardText;
        this.audioCard         =  new AudioCard(context);
        this.frontAnimClockWise = frontAnimClockWise;
        this.backAnimClockWise  = backAnimClockWise;
        this.frontAnimAntiClockWise   = frontAnimAntiClockWise;
        this.backAnimAntiClockWise    = backAnimAntiClockWise;
        this.scale             = context.getApplicationContext().getResources().getDisplayMetrics().density;
        this.isFront           = true;
        this.isBack            = true;
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

    public void showControlDifficultButton(boolean show) {
        if (show) {
            easyButton.setVisibility(Button.VISIBLE);
            goodButton.setVisibility(Button.VISIBLE);
            hardButton.setVisibility(Button.VISIBLE);
        }else {
            easyButton.setVisibility(Button.INVISIBLE);
            goodButton.setVisibility(Button.INVISIBLE);
            hardButton.setVisibility(Button.INVISIBLE);
        }
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

    private void setInvisibilityFrontCard() {
        this.frontCardViewText.setVisibility(View.INVISIBLE);
    }
    private void setVisibilityFrontCard() {
        this.frontCardViewText.setVisibility(View.VISIBLE);
    }

    public void makeAnimationRight() {
        if (this.isFront) {
            this.frontAnimAntiClockWise.setTarget(this.frontCardViewText);
            this.backAnimAntiClockWise.setTarget(this.backCardViewText);
            frontAnimAntiClockWise.start();
            backAnimAntiClockWise.start();
            this.isFront = false;
            this.isBack  = false;
            this.frontCardViewText.postDelayed(this::setInvisibilityFrontCard, 1000);
            this.showControlDifficultButton(true);
        } else {
            this.frontAnimAntiClockWise.setTarget(this.backCardViewText);
            this.backAnimAntiClockWise.setTarget(this.frontCardViewText);
            backAnimAntiClockWise.start();
            frontAnimAntiClockWise.start();
            this.isFront = true;
            this.isBack  = true;
            this.showControlDifficultButton(false);
            this.setVisibilityFrontCard();
        }
    }


    public void makeAnimationLeft() {
        if (this.isBack) {
            this.frontAnimClockWise.setTarget(this.frontCardViewText);
            this.backAnimClockWise.setTarget(this.backCardViewText);
            frontAnimClockWise.start();
            backAnimClockWise.start();
            this.isBack = false;
            this.isFront = false;
            this.frontCardViewText.postDelayed(this::setInvisibilityFrontCard, 1000);
        } else {
            this.frontAnimClockWise.setTarget(this.backCardViewText);
            this.backAnimClockWise.setTarget(this.frontCardViewText);
            backAnimClockWise.start();
            frontAnimClockWise.start();
            this.isBack = true;
            this.isFront = true;
            this.setVisibilityFrontCard();
        }
    }

}

package com.example.flashcards_app.models;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.flashcards_app.models.AudioCard;
import com.example.flashcards_app.R;
import androidx.appcompat.app.AppCompatActivity;


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
        this.makeAnimation();
    }

    public void goodButtonCommand() {
        this.makeAnimation();
    }

    public void hardButtonCommand() {
        this.makeAnimation();
    }
    public void audioSpeak() {
        this.audioCard.speak("Hello, this is a sample text to be spoken in English.");
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

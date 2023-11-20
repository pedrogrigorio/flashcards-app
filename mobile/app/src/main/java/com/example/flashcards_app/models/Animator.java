/*
* Created by: Richard Lira
* Date: 08/10/2023
* Function: Class responsible for performing the animation calculations that take place on the card.
*/


package com.example.flashcards_app.models;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.ReviewActivity;

public class Animator {

    private AnimatorSet frontAnimAntiClockWise;
    private AnimatorSet backAnimAntiClockWise;
    private AnimatorSet frontAnimClockWise;
    private AnimatorSet backAnimClockWise;
    private float scale;
    private View frontCardViewText;
    private View backCardViewText;
    private boolean turnControl = true;

    public Animator(Context context,
                    AnimatorSet frontAnimAntiClockWise,
                    AnimatorSet backAnimAntiClockWise,
                    AnimatorSet frontAnimClockWise,
                    AnimatorSet backAnimClockWise,
                    View frontCardViewText,
                    View backCardViewText) {

        this.frontCardViewText = frontCardViewText;
        this.backCardViewText  = backCardViewText;
        this.frontAnimAntiClockWise = frontAnimAntiClockWise;
        this.backAnimAntiClockWise  = backAnimAntiClockWise;
        this.frontAnimClockWise = frontAnimClockWise;
        this.backAnimClockWise  = backAnimClockWise;
        this.scale = context.getApplicationContext().getResources().getDisplayMetrics().density;

        this.setCameraCardDistance();
    }
    private void setCameraCardDistance() {
        this.frontCardViewText.setCameraDistance(8000*this.scale);
        this.backCardViewText.setCameraDistance(8000*this.scale);
    }


    private void mainAnimationEngine(int animationId) {

        switch (animationId) {
            case 1:
                if (this.turnControl) {
                this.frontAnimClockWise.setTarget(this.frontCardViewText);
                this.backAnimClockWise.setTarget(this.backCardViewText);
                frontAnimClockWise.start();
                backAnimClockWise.start();
                this.turnControl = false;

            } else {
                this.frontAnimClockWise.setTarget(this.backCardViewText);
                this.backAnimClockWise.setTarget(this.frontCardViewText);
                backAnimClockWise.start();
                frontAnimClockWise.start();
                this.turnControl = true;
            }
            break;

            case 2:
                if (this.turnControl) {
                this.frontAnimAntiClockWise.setTarget(this.frontCardViewText);
                this.backAnimAntiClockWise.setTarget(this.backCardViewText);
                frontAnimAntiClockWise.start();
                backAnimAntiClockWise.start();
                this.turnControl = false;
            } else {
                this.frontAnimAntiClockWise.setTarget(this.backCardViewText);
                this.backAnimAntiClockWise.setTarget(this.frontCardViewText);
                backAnimAntiClockWise.start();

                frontAnimAntiClockWise.start();
                this.turnControl = true;

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

}

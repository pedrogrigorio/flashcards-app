package com.example.flashcards_app.viewmodel.ViewModelLogic.Review;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class AudioCard {

    private TextToSpeech textToSpeech;


    public AudioCard(Context context) {
        this.textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

    }


    public void speak(String text){
        if (this.textToSpeech != null) {
            this.textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    public void shutDown() {
        if (this.textToSpeech != null) {
            this.textToSpeech.stop();
            this.textToSpeech.shutdown();
        }
    }


}

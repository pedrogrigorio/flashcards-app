package com.example.flashcards_app.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewViewModel extends ViewModel {
    private MutableLiveData<List<Review>> reviewLiveData = new MutableLiveData<>();
    private List<Review> reviewTemp = new ArrayList<>();


    public ReviewViewModel() {
        loadReviewCardsData();
    }

    private void loadReviewCardsData() {
        reviewTemp.add(new Review("Good Nigth", "Boa Noite", 1, 1));
        reviewTemp.add(new Review("Have a nice day", "Tenha um bom dia", 2,null));
        reviewTemp.add(new Review("So far, so good", "Até agora, tudo bem", 3,null));
        reviewTemp.add(new Review("I'm lost", "Eu estou perdido", 4,null));


    }

    public void loadUiCards(int indexUiCard, Context context) {
        List<Review> tempLiveData = new ArrayList<>();

        for (Review review : reviewTemp) {
            if (review.getStampLevel() == null) {
                tempLiveData.add(review);
                break;
            }
            else if (review.getStampLevel() != null){
                tempLiveData.add(review);
            }
        }

        this.reviewLiveData.setValue(tempLiveData);
    }



    public LiveData<List<Review>> getReviewData() {
        return this.reviewLiveData;

    }

}

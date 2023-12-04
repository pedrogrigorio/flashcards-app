package com.example.flashcards_app.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.activities.ReviewActivity;
import com.example.flashcards_app.adapters.ReviewAdapter;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Review;
import com.example.flashcards_app.repositories.CardRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ReviewViewModel extends ViewModel {
    private MutableLiveData<List<Review>> reviewLiveData = new MutableLiveData<>();
    private List<Review> reviewTemp = new ArrayList<>();
    private OnRefreshProgressBar onRefreshProgressBar;
    private CardRepository cardRepository;
    private int indexLastCardReviewed;

    public ReviewViewModel() {
        cardRepository = new CardRepository();
    }

    public void loadReviewCardsData(LifecycleOwner owner, int deckId) {
        System.out.println("RODOU ANTES DO IF");
        cardRepository.getCardsForToday(deckId).observe(owner, reviews -> {
            reviewTemp.addAll(reviews);
            System.out.println("DENTRO DO OBSERVER");
            loadUiCards();
        });

        System.out.println("RODOU DEPOIS DO IF");
    }

    public void loadUiCards() {
        List<Review> tempLiveData = new ArrayList<>();

        for (Review review : reviewTemp) {
            if (review.getStampLevel() == null) {
                tempLiveData.add(review);
                setIndexLastCardReviewed();
                break;
            }
            else if (review.getStampLevel() != null){
                tempLiveData.add(review);
            }
        }

        if (tempLiveData.equals(reviewLiveData.getValue())) return;


        this.reviewLiveData.setValue(tempLiveData);
    }

    public void deleteCard(int deckId, int position) {
        List<Review> currentCards = reviewLiveData.getValue();

        if (currentCards != null && !currentCards.isEmpty()) {
            cardRepository.deleteCard(deckId, currentCards.get(position).getId());
            currentCards.remove(position);
            reviewTemp.remove(position);

            reviewLiveData.setValue(currentCards);
        }

        setIndexLastCardReviewed();
    }


    private void setIndexLastCardReviewed() {
        if (reviewLiveData.getValue() != null && !reviewLiveData.getValue().isEmpty()) {
            for (Review review: reviewLiveData.getValue()) {
                if (review.getStampLevel() == null) {
                    indexLastCardReviewed = reviewLiveData.getValue().indexOf(review);
                    onRefreshProgressBar.refreshProgressBar();
                    return;
                }
            }

            indexLastCardReviewed = reviewLiveData.getValue().size();
        }
    }

    public void updateCard(Review updatedCard, int position, int deckId) {
        List<Review> currentCards = reviewLiveData.getValue();

        if (currentCards != null && !currentCards.isEmpty()) {
            currentCards.set(position, updatedCard);
            cardRepository.updateCard(deckId, currentCards.get(position).getId(), updatedCard.getFrontText(), updatedCard.getBackText());
            reviewTemp.set(position, updatedCard);

            reviewLiveData.setValue(currentCards);
        }

        setIndexLastCardReviewed();
    }
    public Review getCurrentCard(int position) {
        return reviewLiveData.getValue().get(position);
    }


    public void setReviewedCard(int ActualFirstVisibleItemPosition, int StampLevel) {
        this.reviewTemp.get(ActualFirstVisibleItemPosition).setStampLevel(StampLevel);
    }

    public boolean hasBeenReviewed(int ActualFirstVisibleItemPosition) {
        return this.reviewTemp.get(ActualFirstVisibleItemPosition).getStampLevel() != null;
    }

    public int getLoadCardsSize() {
        return this.reviewTemp.size();
    }

    public int getIndexLastCardReviewed() {
        return this.indexLastCardReviewed;
    }

    public LiveData<List<Review>> getReviewData() {
        return this.reviewLiveData;

    }

    public interface OnRefreshProgressBar {
        void refreshProgressBar();
    }

    public void setOnRefreshProgressBar(OnRefreshProgressBar onRefreshProgressBar) {
        this.onRefreshProgressBar = onRefreshProgressBar;
    }
}

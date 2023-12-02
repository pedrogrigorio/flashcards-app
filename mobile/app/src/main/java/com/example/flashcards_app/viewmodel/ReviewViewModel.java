package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.activities.ReviewActivity;
import com.example.flashcards_app.adapters.ReviewAdapter;
import com.example.flashcards_app.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewViewModel extends ViewModel {
    private MutableLiveData<List<Review>> reviewLiveData = new MutableLiveData<>();
    private List<Review> reviewTemp = new ArrayList<>();
    private OnRefreshProgressBar onRefreshProgressBar;
    private int indexLastCardReviewed;
    public ReviewViewModel() {
        loadReviewCardsData();

    }

    private void loadReviewCardsData() {
        reviewTemp.add(new Review("Good Nigth", "Boa Noite", 0, null));
        reviewTemp.add(new Review("Have a nice day", "Tenha um bom dia", 1,null));
        reviewTemp.add(new Review("So far, so good", "Até agora, tudo bem", 2,null));
        reviewTemp.add(new Review("I'm lost", "Eu estou perdido", 3,null));


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

    public void deleteCard(int position) {
        List<Review> currentCards = reviewLiveData.getValue();

        if (currentCards != null && !currentCards.isEmpty()) {
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

    public void updateCard(Review updatedCard, int position) {
        List<Review> currentCards = reviewLiveData.getValue();

        if (currentCards != null && !currentCards.isEmpty()) {
            currentCards.set(position, updatedCard);
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

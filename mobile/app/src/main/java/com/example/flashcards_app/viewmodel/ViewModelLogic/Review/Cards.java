package com.example.flashcards_app.viewmodel.ViewModelLogic.Review;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.adapters.ReviewAdapter;
import com.example.flashcards_app.models.Review;

import java.util.List;

public class Cards {

    public Cards() {

    }


    public void easyButton(int findFirstVisibleItemPosition, RecyclerView recyclerView, List<Review> reviews) {
        ReviewAdapter.ReviewHolder firstVisibleViewHolder = (ReviewAdapter.ReviewHolder) recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);

        if (firstVisibleViewHolder != null) {
            firstVisibleViewHolder.setStampLevel(0);
            Review currentReview = reviews.get(findFirstVisibleItemPosition);
            currentReview.setStampLevel(0);

        }
    }

    public void goodButton(int findFirstVisibleItemPosition, RecyclerView recyclerView, List<Review> reviews) {
        ReviewAdapter.ReviewHolder firstVisibleViewHolder = (ReviewAdapter.ReviewHolder) recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);

        if (firstVisibleViewHolder != null) {
            firstVisibleViewHolder.setStampLevel(1);
            Review currentReview = reviews.get(findFirstVisibleItemPosition);
            currentReview.setStampLevel(1);
        }


    }

    public void hardButton(int findFirstVisibleItemPosition, RecyclerView recyclerView, List<Review> reviews) {
        ReviewAdapter.ReviewHolder firstVisibleViewHolder = (ReviewAdapter.ReviewHolder) recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);

        if (firstVisibleViewHolder != null) {
            firstVisibleViewHolder.setStampLevel(2);
            Review currentReview = reviews.get(findFirstVisibleItemPosition);
            currentReview.setStampLevel(2);

        }
    }

}
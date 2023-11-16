package com.example.flashcards_app.viewmodel.ViewModelLogic.Review;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.adapters.ReviewAdapter;

public class Cards {

    public Cards() {

    }


    public void easyButton(int findFirstVisibleItemPosition, RecyclerView recyclerView) {
        ReviewAdapter.ReviewHolder firstVisibleViewHolder = (ReviewAdapter.ReviewHolder) recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
        if (firstVisibleViewHolder != null) {
            firstVisibleViewHolder.setStampLevel(0);
        }
    }

    public void goodButton(int findFirstVisibleItemPosition, RecyclerView recyclerView) {
        ReviewAdapter.ReviewHolder firstVisibleViewHolder = (ReviewAdapter.ReviewHolder) recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
        if (firstVisibleViewHolder != null) {
            firstVisibleViewHolder.setStampLevel(1);
        }

    }

    public void hardButton(int findFirstVisibleItemPosition, RecyclerView recyclerView) {
        ReviewAdapter.ReviewHolder firstVisibleViewHolder = (ReviewAdapter.ReviewHolder) recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
        if (firstVisibleViewHolder != null) {
            firstVisibleViewHolder.setStampLevel(2);
        }
    }

}
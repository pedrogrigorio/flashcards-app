package com.example.flashcards_app.util;

import com.example.flashcards_app.models.Review;

public interface ViewModelAdapterMethods {
    void updateCard(Review updateCard, int position);
    void deleteCard(int position);
}

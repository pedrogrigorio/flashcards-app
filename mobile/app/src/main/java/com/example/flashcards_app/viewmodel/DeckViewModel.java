package com.example.flashcards_app.viewmodel;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Deck;

import java.util.ArrayList;
import java.util.List;

public class DeckViewModel extends ViewModel {
    private MutableLiveData<List<Deck>> decksLiveData = new MutableLiveData<>();

    public LiveData<List<Deck>> getDecks() {
        return decksLiveData;
    }

    public void insertDeck(Deck deck) {
        List<Deck> currentDecks = decksLiveData.getValue();

        if (currentDecks == null) {
            currentDecks = new ArrayList<>();
        }

        currentDecks.add(deck);

        decksLiveData.setValue(currentDecks);
    }

    public void updateDeck(Deck deck, int position) {
        List<Deck> currentDecks = decksLiveData.getValue();

        if (currentDecks != null && !currentDecks.isEmpty()) {
            currentDecks.set(position, deck);

            decksLiveData.setValue(currentDecks);
        }
    }

    public void deleteDeck(int position) {
        List<Deck> currentDecks = decksLiveData.getValue();

        if (currentDecks != null && !currentDecks.isEmpty()) {
            currentDecks.remove(position);

            decksLiveData.setValue(currentDecks);
        }
    }
}

package com.example.flashcards_app.viewmodel;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.repositories.DeckRepository;

import java.util.ArrayList;
import java.util.List;

public class DeckViewModel extends ViewModel {
    private MutableLiveData<List<Deck>> decksLiveData = new MutableLiveData<>();
    private DeckRepository deckRepository;

    public DeckViewModel() {
        deckRepository = new DeckRepository();
    }

    public LiveData<List<Deck>> getDecks() {
        if (decksLiveData.getValue() == null || decksLiveData.getValue().isEmpty()) {
            decksLiveData = deckRepository.getAllDecks();
        }

        return decksLiveData;
    }

    /*
    *   Methods to be implemented in repository
    * =================================================== */
//    public void insertDeck() {
//        List<Deck> temp = deckRepository.addNewDeck().getValue();
//        temp.addAll(decksLiveData.getValue());
//        decksLiveData.setValue(temp);
//
//    }

    public void insertDeck(Deck deck) {
        deckRepository.addNewDeck();

        if(decksLiveData.getValue() != null) {
            List<Deck> temp =  decksLiveData.getValue();
            temp.add(deck);
            decksLiveData.setValue(temp);
        }
    }



    public void updateDeck(Deck deck, int position) {
        deckRepository.updateDeck(deck);
        List<Deck> temp = decksLiveData.getValue();
        if(temp != null) {
            temp.set(position, deck);
            decksLiveData.setValue(temp);
        }

    }

    public void addNewCardDeck(String frontText, String backText, Deck deck) {

    }

    public void deleteDeck(Deck deck, int position) {
        System.out.println(String.valueOf(deck.getId()));
        deckRepository.deleteDeck(deck);
        List<Deck> temp = decksLiveData.getValue();
        if(temp != null) {
            temp.remove(position);
            decksLiveData.setValue(temp);
        }

    }
}

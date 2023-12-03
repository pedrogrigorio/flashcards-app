package com.example.flashcards_app.api;

import com.example.flashcards_app.models.Deck;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeckService {
    @GET("deck/getAllDeck")
    Call<List<Deck>> getAllDecks();


}

package com.example.flashcards_app.api;

import com.example.flashcards_app.models.Deck;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeckService {
    @GET("decks")
    Call<List<Deck>> getAllDecks();
}

package com.example.flashcards_app.api;

import com.example.flashcards_app.dto.CreateCardDTO;
import com.example.flashcards_app.dto.DeleteDeckDTO;
import com.example.flashcards_app.dto.UpdateDeckDTO;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Review;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DeckService {
    @GET("deck/getAllDeck")
    Call<List<Deck>> getAllDecks();

    @GET("/deck/create")
    Call<List<Deck>> createDeck();

    @Multipart
    @PUT("/decK/updateDeck")
    Call<List<Deck>> updateDeck(
            @Part("updateDeckDTO") RequestBody updateDeckDTORequestBody,
            @Part MultipartBody.Part file
    );

    @DELETE("/deck/{deckId}/deleteDeck")
    Call<List<Deck>> deleteDeck(@Path("deckId") int deckId);

    // No usages for now
    @GET("/deck/:id/getDeckById")
    Call<List<Deck>> getDeckById();





}

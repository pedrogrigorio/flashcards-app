package com.example.flashcards_app.api;

import com.example.flashcards_app.dto.CreateCardDTO;
import com.example.flashcards_app.dto.DeleteCardDTO;
import com.example.flashcards_app.dto.GetCardsForTodayDTO;
import com.example.flashcards_app.dto.UpdateCardDTO;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CardService {

    @POST("/card/create")
    Call<List<Review>> createCard(@Body CreateCardDTO createCardDTO);

    @POST("/card/getCardsForToday")
    Call<List<Review>> getCardsForToday(@Body GetCardsForTodayDTO getCardsForTodayDTO);

    //Corrigir debito aqui depois
    @POST("/card/deleteCard")
    Call<List<Review>> deleteCard(@Body DeleteCardDTO deleteCardDTO);

    @PUT("/card/updateCard")
    Call<List<Review>> updateCard(@Body UpdateCardDTO updateCardDTO);
}

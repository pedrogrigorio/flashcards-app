package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.CardService;
import com.example.flashcards_app.api.DeckService;
import com.example.flashcards_app.dto.CreateCardDTO;
import com.example.flashcards_app.dto.DeleteCardDTO;
import com.example.flashcards_app.dto.DeleteDeckDTO;
import com.example.flashcards_app.dto.GetCardsForTodayDTO;
import com.example.flashcards_app.dto.UpdateCardDTO;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Review;
import com.example.flashcards_app.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CardRepository {

    private CardService cardService;
    public CardRepository() {
        cardService = RetrofitClient.getRetrofitInstance().create(CardService.class);
    }

    public MutableLiveData<List<Review>> addNewCardDeck(String frontText, String backText, Deck deck) {
        MutableLiveData<List<Review>> cardsLiveData = new MutableLiveData<>();
        CreateCardDTO createCardDTO = new CreateCardDTO(deck.getId(), frontText, backText);

        Call<List<Review>> call = cardService.createCard(createCardDTO);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    cardsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                // error treatment
                System.out.println("PRINT: add cards error");

                // Some day I will back here
            }
        });


        return cardsLiveData;

    }

    public MutableLiveData<List<Review>> getCardsForToday(int deck) {
        MutableLiveData<List<Review>> cardsLiveData = new MutableLiveData<>();
        GetCardsForTodayDTO getCardsForTodayDTO = new GetCardsForTodayDTO(deck);

        Call<List<Review>> call = cardService.getCardsForToday(getCardsForTodayDTO);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    cardsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                // error treatment
                System.out.println("PRINT: get Cards for Today error");

                // Some day I will back here
            }
        });


        return cardsLiveData;

    }

    public MutableLiveData<List<Review>> deleteCard(int deckId, int cardId) {
        MutableLiveData<List<Review>> cardsLiveData = new MutableLiveData<>();
        DeleteCardDTO DeleteCardDTO =  new DeleteCardDTO(deckId, cardId);

        Call<List<Review>> call = cardService.deleteCard(DeleteCardDTO);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    cardsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                // error treatment
                System.out.println("PRINT: delete card error");

                // Some day I will back here
            }
        });


        return cardsLiveData;

    }

    public MutableLiveData<List<Review>> updateCard(int deckId, int cardId, String frontText, String backText) {
        MutableLiveData<List<Review>> cardsLiveData = new MutableLiveData<>();
        UpdateCardDTO updateCardDTO =  new UpdateCardDTO(deckId, cardId, frontText, backText);

        Call<List<Review>> call = cardService.updateCard(updateCardDTO);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    cardsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                // error treatment
                System.out.println("PRINT: delete card error");

                // Some day I will back here
            }
        });


        return cardsLiveData;

    }



}

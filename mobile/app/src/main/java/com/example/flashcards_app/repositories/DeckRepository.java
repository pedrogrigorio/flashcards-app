package com.example.flashcards_app.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.DeckService;
import com.example.flashcards_app.models.Deck;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeckRepository {
    private DeckService deckService;

    public DeckRepository() {
//        deckService = RetrofitClient.getRetrofitInstance("").create(DeckService.class);
    }

    public MutableLiveData<List<Deck>> getAllDecks() {
        MutableLiveData<List<Deck>> decksLiveData = new MutableLiveData<>();

        Call<List<Deck>> call = deckService.getAllDecks();
        call.enqueue(new Callback<List<Deck>>() {
            @Override
            public void onResponse(Call<List<Deck>> call, Response<List<Deck>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("PRINT: requisition OK");
                    decksLiveData.setValue(response.body());
                } else {
                    // error treatment
                    System.out.println("PRINT: getAllDecks error");
                }
            }

            @Override
            public void onFailure(Call<List<Deck>> call, Throwable t) {
                System.out.println("PRINT: requisition fail " + t.getMessage());
                t.printStackTrace();
            }
        });

        return decksLiveData;
    }
}

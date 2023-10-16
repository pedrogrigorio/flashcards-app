package com.example.flashcards_app.repositories;


import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.DeckService;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeckRepository {
    private DeckService deckService;

    public DeckRepository() {
        deckService = RetrofitClient.getRetrofitInstance().create(DeckService.class);
    }

    public MutableLiveData<List<Deck>> getAllDecks() {
        MutableLiveData<List<Deck>> decksLiveData = new MutableLiveData<>();

        Call<List<Deck>> call = deckService.getAllDecks();
        call.enqueue(new Callback<List<Deck>>() {
            @Override
            public void onResponse(Call<List<Deck>> call, Response<List<Deck>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    decksLiveData.setValue(response.body());
                } else {
                    // error treatment
                    System.out.println("getAllDecks error");
                }
            }

            @Override
            public void onFailure(Call<List<Deck>> call, Throwable t) {

            }
        });

        return decksLiveData;
    }
}

package com.example.flashcards_app.repositories;


import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.DeckService;
import com.example.flashcards_app.dto.CreateCardDTO;
import com.example.flashcards_app.dto.DeleteDeckDTO;
import com.example.flashcards_app.dto.UpdateDeckDTO;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Review;
import com.example.flashcards_app.network.RetrofitClient;
import com.example.flashcards_app.util.AppPreferences;
import com.example.flashcards_app.util.RealPathUtil;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    public MutableLiveData<List<Deck>> updateDeck(Context context, Deck deck, Uri newImgUri) {
        MutableLiveData<List<Deck>> decksLiveData = new MutableLiveData<>();
        MultipartBody.Part filePart = null;
        UpdateDeckDTO updateDeckDTO = new UpdateDeckDTO(deck.getId(),
                deck.getTitle(),
                deck.getImgSrc(),
                deck.getNewCardsNumber(),
                deck.getLearnCardsNumber(),
                deck.getReviewCardsNumber());

        Gson gson = new Gson();

        String updateDeckDTOJson = gson.toJson(updateDeckDTO);
        RequestBody updateDeckDTORequestBody = RequestBody.create(MediaType.parse("application/json"), updateDeckDTOJson);

        if(newImgUri != null) {
            String path = RealPathUtil.getRealPath(context, newImgUri);
            File file = new File(path);

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        }

        Call<List<Deck>> call = deckService.updateDeck(updateDeckDTORequestBody, filePart);

        call.enqueue(new Callback<List<Deck>>() {
            @Override
            public void onResponse(Call<List<Deck>> call, Response<List<Deck>> response) {
                if(response.isSuccessful() && response.body() !=null) {
                    System.out.println("Deu certo para rodar o update");
                    decksLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Deck>> call, Throwable t) {
                // error treatment
                System.out.println("PRINT: update deck error");

                // Some day I will back here
            }
        });

        return decksLiveData;

    }

    public MutableLiveData<List<Deck>> addNewDeck() {
        MutableLiveData<List<Deck>> decksLiveData = new MutableLiveData<>();

        Call<List<Deck>> call = deckService.createDeck();

        call.enqueue(new Callback<List<Deck>>() {
            @Override
            public void onResponse(Call<List<Deck>> call, Response<List<Deck>> response) {
                if(response.isSuccessful() && response.body() !=null) {
                    decksLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Deck>> call, Throwable t) {
                // error treatment
                System.out.println("PRINT: add new deck error");

                // Some day I will back here
            }
        });

        return decksLiveData;
    }

    public MutableLiveData<List<Deck>> deleteDeck(Deck deck) {
        MutableLiveData<List<Deck>> decksLiveData = new MutableLiveData<>();
        DeleteDeckDTO deleteDeckDTO = new DeleteDeckDTO(deck.getId());

        Call<List<Deck>> call = deckService.deleteDeck(deck.getId());


        call.enqueue(new Callback<List<Deck>>() {
            @Override
            public void onResponse(Call<List<Deck>> call, Response<List<Deck>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    decksLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Deck>> call, Throwable t) {
                // error treatment
                System.out.println("PRINT: delete decks error");

                // Some day I will back here
            }
        });


        return decksLiveData;

    }



}

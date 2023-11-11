package com.example.flashcards_app.repositories;


import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.DeckService;
import com.example.flashcards_app.api.UserService;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private UserService userService;

    public UserRepository() {
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);
    }

    public MutableLiveData<User> getProfile() {
        MutableLiveData<User> profileLiveData = new MutableLiveData<>();

        Call<User> call = userService.getProfile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("PRINT: requisition OK");
                    profileLiveData.setValue(response.body());
                } else {
                    // error treatment
                    System.out.println("PRINT: getAllDecks error");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("PRINT: requisition fail " + t.getMessage());
                t.printStackTrace();
            }
        });

        return profileLiveData;
    }
}

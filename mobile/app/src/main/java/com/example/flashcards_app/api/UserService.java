package com.example.flashcards_app.api;

import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Call<User> getProfile();
}

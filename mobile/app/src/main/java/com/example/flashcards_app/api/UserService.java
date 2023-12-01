package com.example.flashcards_app.api;

import com.example.flashcards_app.dto.RegisterDTO;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {
    @GET("users")
    Call<User> getProfile();

    @POST("users/register")
    Call<Void> register(@Body RegisterDTO registerDTO);
}

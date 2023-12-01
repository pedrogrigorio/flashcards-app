package com.example.flashcards_app.api;

import com.example.flashcards_app.dto.LoginDTO;
import com.example.flashcards_app.dto.RegisterDTO;
import com.example.flashcards_app.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("users")
    Call<User> getProfile();

    @POST("users/register")
    Call<Void> register(@Body RegisterDTO registerDTO);

    @POST("auth")
    Call<String> login(@Body LoginDTO loginDTO);
}

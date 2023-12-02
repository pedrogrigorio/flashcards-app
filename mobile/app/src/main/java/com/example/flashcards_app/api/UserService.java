package com.example.flashcards_app.api;

import com.example.flashcards_app.dto.LoginDTO;
import com.example.flashcards_app.dto.RegisterDTO;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.models.UserAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("users/register")
    Call<Void> register(@Body RegisterDTO registerDTO);

    @POST("auth")
    Call<UserAuth> login(@Body LoginDTO loginDTO);
}

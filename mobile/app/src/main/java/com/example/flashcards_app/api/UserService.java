package com.example.flashcards_app.api;

import com.example.flashcards_app.dto.LoginDTO;
import com.example.flashcards_app.dto.RegisterDTO;
import com.example.flashcards_app.dto.SearchUsersDTO;
import com.example.flashcards_app.dto.UpdateProfileDTO;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.models.UserAuth;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("users/register")
    Call<Void> register(@Body RegisterDTO registerDTO);

    @POST("auth")
    Call<UserAuth> login(@Body LoginDTO loginDTO);

    @PUT("users/{id}/profile")
    Call<User> updateName(@Path("id") String id, @Body UpdateProfileDTO updateProfileDTO);

    @Multipart
    @PUT("users/{id}/profile")
    Call<User> updateProfile(@Path("id") String id, @Part MultipartBody.Part file, @Part("name") RequestBody name);

    @POST("users/search")
    Call<List<User>> searchUsers(@Body SearchUsersDTO searchUsersDTO);

//    @PUT("users/{id}/stats")

//    @PUT("users/{id}/dayStreak")
}

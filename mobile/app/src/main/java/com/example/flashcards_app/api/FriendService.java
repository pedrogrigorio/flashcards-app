package com.example.flashcards_app.api;

import com.example.flashcards_app.models.Friend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FriendService {
    @GET("friends")
    Call<List<Friend>> getAllFriends();

    @DELETE("friends/{id}")
    Call<Void> deleteFriend(@Path("id") String id);
}

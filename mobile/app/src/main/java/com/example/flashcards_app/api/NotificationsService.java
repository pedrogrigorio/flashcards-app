package com.example.flashcards_app.api;

import com.example.flashcards_app.models.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationsService {

    @GET("notifications")
    Call<List<Notification>> lookForNotification();

}

package com.example.flashcards_app.api;

import com.example.flashcards_app.models.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationService {

    @GET("Notification")
    Call<List<Notification>> lookForNotification();

}

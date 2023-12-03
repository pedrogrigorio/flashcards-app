package com.example.flashcards_app.api;

import com.example.flashcards_app.dto.SendFriendRequestDTO;
import com.example.flashcards_app.models.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NotificationService {

    @GET("notifications")
    Call<List<Notification>> getAllNotifications();

    @POST("notifications")
    Call<Void> sendFriendRequest(@Body SendFriendRequestDTO sendFriendRequestDTO);

    @PUT("notifications/{id}/accept")
    Call<Notification> acceptFriendRequest(@Path("id") String id);

    @PUT("notifications/{id}/reject")
    Call<Notification> rejectFriendRequest(@Path("id") String id);
}

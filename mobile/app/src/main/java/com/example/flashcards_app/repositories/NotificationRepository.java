package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.NotificationService;
import com.example.flashcards_app.dto.SendFriendRequestDTO;
import com.example.flashcards_app.models.Notification;
import com.example.flashcards_app.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {
    private NotificationService notificationService;

    public NotificationRepository() {
        notificationService = RetrofitClient.getRetrofitInstance().create(NotificationService.class);
    }

    public MutableLiveData<List<Notification>> getAllNotifications() {

        MutableLiveData<List<Notification>> notificationLiveData = new MutableLiveData<>();

        Call<List<Notification>> call = notificationService.getAllNotifications();
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    notificationLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                // Some day I will back here
            }
        });

        return notificationLiveData;
    }

    public MutableLiveData<Boolean> sendFriendRequest(int receiverId) {
        MutableLiveData<Boolean> notificationSent = new MutableLiveData<>();

        SendFriendRequestDTO sendFriendRequestDTO = new SendFriendRequestDTO(receiverId);

        Call<Void> call = notificationService.sendFriendRequest(sendFriendRequestDTO);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    notificationSent.setValue(true);
                }
                else {
                    notificationSent.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });

        return notificationSent;
    }

    public MutableLiveData<Notification> acceptFriendRequest(int notificationId) {
        MutableLiveData<Notification> updatedNotification = new MutableLiveData<>();

        Call<Notification> call = notificationService.acceptFriendRequest(String.valueOf(notificationId));
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if (response.isSuccessful()) {
                    updatedNotification.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

            }
        });

        return updatedNotification;
    }

    public MutableLiveData<Notification> rejectFriendRequest(int notificationId) {
        MutableLiveData<Notification> updatedNotification = new MutableLiveData<>();

        Call<Notification> call = notificationService.rejectFriendRequest(String.valueOf(notificationId));
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if (response.isSuccessful()) {
                    updatedNotification.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

            }
        });

        return updatedNotification;
    }
}

package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.NotificationService;
import com.example.flashcards_app.models.Notification;
import com.example.flashcards_app.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {

    private NotificationService notificationService;


    public NotificationRepository() {
        notificationService = RetrofitClient.getRetrofitInstance().create(NotificationService.class);
    }

    public MutableLiveData<List<Notification>> getNotifications() {

        MutableLiveData<List<Notification>> notificationLiveData = new MutableLiveData<>();

        Call<List<Notification>> call = notificationService.lookForNotification();
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


}

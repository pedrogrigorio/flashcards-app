package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.NotificationsService;
import com.example.flashcards_app.models.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsRepository {

    private NotificationsService notificationsService;


    public NotificationsRepository() {
//        notificationsService = RetrofitClient.getRetrofitInstance("").create(NotificationsService.class);
    }

    public MutableLiveData<List<Notification>> getNotifications() {

        MutableLiveData<List<Notification>> notificationLiveData = new MutableLiveData<>();

        Call<List<Notification>> call = notificationsService.lookForNotification();
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

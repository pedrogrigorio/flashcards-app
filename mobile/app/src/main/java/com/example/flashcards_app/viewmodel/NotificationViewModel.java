package com.example.flashcards_app.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Notification;
import com.example.flashcards_app.repositories.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

public class NotificationViewModel extends ViewModel {

    private MutableLiveData<List<Notification>> notificationsLiveData = new MutableLiveData<>();
    private NotificationRepository notificationRepository;


    public NotificationViewModel() {
        notificationRepository = new NotificationRepository();
    }

    public LiveData<List<Notification>> getAllNotifications() {
        notificationsLiveData = notificationRepository.getAllNotifications();

        return notificationsLiveData;
    }

    public LiveData<Notification> acceptFriendRequest(Notification notification) {
        MutableLiveData<Notification> notificationLiveData;

        notificationLiveData = notificationRepository.acceptFriendRequest(notification.getId());

        return notificationLiveData;
    }

    public LiveData<Notification> rejectFriendRequest(Notification notification) {
        MutableLiveData<Notification> notificationLiveData;

        notificationLiveData = notificationRepository.rejectFriendRequest(notification.getId());

        return notificationLiveData;
    }

}

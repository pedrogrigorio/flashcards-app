package com.example.flashcards_app.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Notification;
import com.example.flashcards_app.repositories.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

public class NotificationViewModel extends ViewModel {

    private MutableLiveData<List<Notification>> notificationLiveData = new MutableLiveData<>();
    private NotificationRepository notificationRepository;


    public NotificationViewModel() {
        notificationRepository = new NotificationRepository();
    }

    public LiveData<List<Notification>> getNotification() {
        if (notificationLiveData.getValue() == null || notificationLiveData.getValue().isEmpty()) {
            notificationLiveData = notificationRepository.getNotifications();
        }

//        MutableLiveData<List<Notification>> tempDataLive = new MutableLiveData<>();
//        List<Notification> tempData = new ArrayList<>();
//
//        tempData.add(new Notification("Ficando Gigante"));
//        tempData.add(new Notification("140kg"));
//        tempData.add(new Notification("Ficando monstro"));
//        tempData.add(new Notification("Bora buscar"));
//
//
//        tempDataLive.setValue(tempData);
//        notificationLiveData = tempDataLive;

        return notificationLiveData;
    }


    // Metodo de teste para adicionar novas notificações na tela ( Isso deve sumir logo logo )
    public void insertNotification(Notification notification, Context context) {
        List<Notification> currentNotification = notificationLiveData.getValue();

        if (currentNotification == null) {
            currentNotification = new ArrayList<>();
        }

        currentNotification.add(notification);

        notificationLiveData.setValue(currentNotification);

    }

}

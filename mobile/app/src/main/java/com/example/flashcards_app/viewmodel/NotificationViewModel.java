package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationViewModel {

    private MutableLiveData<List<Notification>> notificationLiveData = new MutableLiveData<>();

    public LiveData<List<Notification>> getNotification() {
        //        if (addFriendsLiveData.getValue() == null || addFriendsLiveData.getValue().isEmpty()) {
        //            addFriendsLiveData = addFriendRepository.getNewFriendsToAdd();
        //        }

        MutableLiveData<List<Notification>> tempDataLive = new MutableLiveData<>();
        List<Notification> tempData = new ArrayList<>();

        tempData.add(new Notification("Ficando Gigante"));
        tempData.add(new Notification("140kg"));
        tempData.add(new Notification("Ficando monstro"));
        tempData.add(new Notification("Bora buscar"));

        tempDataLive.setValue(tempData);
        return tempDataLive;
    }

}

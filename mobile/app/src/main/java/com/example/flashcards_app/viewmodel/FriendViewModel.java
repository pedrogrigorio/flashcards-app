package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.repositories.FriendRepository;

import java.util.ArrayList;
import java.util.List;

public class FriendViewModel extends ViewModel {
    private MutableLiveData<List<Friend>> friendsLiveData = new MutableLiveData<>();
    private FriendRepository friendRepository;

    public FriendViewModel() {
        friendRepository = new FriendRepository();
    }

    public LiveData<List<Friend>> getAllFriends() {
        if (friendsLiveData.getValue() == null || friendsLiveData.getValue().isEmpty()) {
            friendsLiveData = friendRepository.getAllFriends();
        }

        return friendsLiveData;
    }

    public LiveData<Boolean> deleteFriend(Friend friend) {
        MutableLiveData<Boolean> friendDeleted;
        friendDeleted = friendRepository.deleteFriend(friend.getUserId());

        return friendDeleted;
    }
}

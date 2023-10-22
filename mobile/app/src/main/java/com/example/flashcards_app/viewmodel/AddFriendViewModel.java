package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.AddFriendRepository;

import java.util.ArrayList;
import java.util.List;

public class AddFriendViewModel extends ViewModel {

    private MutableLiveData<List<User>> addFriendsLiveData = new MutableLiveData<>();
    private AddFriendRepository addFriendRepository;

    public AddFriendViewModel() {
        addFriendRepository = new AddFriendRepository();
    }


    public LiveData<List<User>> getAddFriends() {
        if (addFriendsLiveData.getValue() == null || addFriendsLiveData.getValue().isEmpty()) {
            addFriendsLiveData = addFriendRepository.getNewFriendsToAdd();
        }

        return addFriendsLiveData;
    }
    public void insertFriend(User user) {
        List<User> currentFriends = addFriendsLiveData.getValue();

        if (currentFriends == null) {
            currentFriends = new ArrayList<>();
        }

        currentFriends.add(user);

        addFriendsLiveData.setValue(currentFriends);
    }



//    public void deleteFriend(int position) {
//        Adicionar funcionalidade em breve
//    }



}

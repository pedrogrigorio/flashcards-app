package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.NotificationRepository;
import com.example.flashcards_app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class AddUserViewModel extends ViewModel {
    private MutableLiveData<Boolean> notificationSent = new MutableLiveData<>();
    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    public AddUserViewModel() {
        userRepository = new UserRepository();
        notificationRepository = new NotificationRepository();
    }


    public MutableLiveData<List<User>> searchUsers(String query) {
        usersLiveData = userRepository.searchUsers(query);

        return usersLiveData;
    }

    public LiveData<Boolean> sendFriendRequest(User user) {
        notificationSent = notificationRepository.sendFriendRequest(user.getId());
        System.out.println("OOOOOOOOOOOOOOOOOOOOIIIIIIIIIIIIIIIIIIIII");

        return notificationSent;
    }

    public void deleteFriend(User user) {
//        List<User> currentUsers = usersLiveData.getValue();
//
//        if (currentUsers == null) {
//            currentUsers = new ArrayList<>();
//        }
//
//        currentUsers.set(position, user);
//
//        usersLiveData.setValue(currentUsers);
    }



}

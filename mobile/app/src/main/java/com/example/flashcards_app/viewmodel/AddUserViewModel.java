package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.AddUserRepository;
import com.example.flashcards_app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class AddUserViewModel extends ViewModel {

    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private UserRepository userRepository;

    public AddUserViewModel() {
        userRepository = new UserRepository();
    }


    public MutableLiveData<List<User>> searchUsers(String query) {

        usersLiveData = userRepository.searchUsers(query);

        return usersLiveData;

    }


    public User getUser(int position) {
        return usersLiveData.getValue().get(position);
    }

    public void deleteFriend(User user, int position) {
        List<User> currentUsers = usersLiveData.getValue();

        if (currentUsers == null) {
            currentUsers = new ArrayList<>();
        }

        currentUsers.set(position, user);

        usersLiveData.setValue(currentUsers);
    }



}

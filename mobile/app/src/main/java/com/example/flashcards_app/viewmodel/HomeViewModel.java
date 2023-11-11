package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.UserRepository;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<User> profileLiveData = new MutableLiveData<>();
    private UserRepository userRepository;

    public HomeViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<User> getProfile() {
        if (profileLiveData.getValue() == null) {
            profileLiveData = userRepository.getProfile();
        }

        return profileLiveData;
    }
}

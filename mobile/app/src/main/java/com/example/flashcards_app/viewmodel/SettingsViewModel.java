package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.UserRepository;

public class SettingsViewModel extends ViewModel {
    private MutableLiveData<User> profileLiveData = new MutableLiveData<>();
    private UserRepository userRepository;

    public SettingsViewModel() {
//        userRepository = new UserRepository("");
    }

    public LiveData<User> getProfile() {
        if (profileLiveData.getValue() == null) {
//            profileLiveData = userRepository.getUser();
        }

        return profileLiveData;
    }

    public void updateProfile(User user) {
        profileLiveData.setValue(user);
    }
}

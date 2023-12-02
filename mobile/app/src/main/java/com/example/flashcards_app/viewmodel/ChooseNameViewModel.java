package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.dto.UpdateProfileDTO;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.models.UserAuth;
import com.example.flashcards_app.repositories.UserRepository;

public class ChooseNameViewModel extends ViewModel {
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private UserRepository userRepository;

    public ChooseNameViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<User> updateProfile(String userId, String name) {
        userLiveData = userRepository.updateProfile(userId, name);

        return userLiveData;
    };
}

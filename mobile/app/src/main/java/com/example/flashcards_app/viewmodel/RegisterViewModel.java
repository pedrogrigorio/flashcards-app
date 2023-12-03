package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.UserRepository;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
    private UserRepository userRepository;

    public RegisterViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<Boolean> register(String username, String email, String password) {
        registerSuccess = userRepository.register(username, email, password);

        return registerSuccess;
    };
}

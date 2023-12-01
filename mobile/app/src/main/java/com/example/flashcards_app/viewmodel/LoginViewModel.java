package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.repositories.UserRepository;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> token = new MutableLiveData<>();
    private UserRepository userRepository;

    public LoginViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<String> login(String email, String password) {
        token = userRepository.login(email, password);

        return token;
    };
}

package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.UserAuth;
import com.example.flashcards_app.repositories.UserRepository;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<UserAuth> authData = new MutableLiveData<>();
    private UserRepository userRepository;

    public LoginViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<UserAuth> login(String email, String password) {
        authData = userRepository.login(email, password);

        return authData;
    };
}

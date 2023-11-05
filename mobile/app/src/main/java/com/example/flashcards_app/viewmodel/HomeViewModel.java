package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.User;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
//    private UserRepository userRepository;

    public LiveData<User> getProfile() {
        if (userLiveData.getValue() == null) {
//            profileLiveData = userRepository.getSomething();
            userLiveData.setValue(new User("User3", "username3"));
        }

        return userLiveData;
    }

    public void updateProfile(User user) {
        userLiveData.setValue(user);
    }
}

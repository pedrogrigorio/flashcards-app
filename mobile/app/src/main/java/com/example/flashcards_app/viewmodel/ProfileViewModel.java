package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.models.User;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<User> profileLiveData = new MutableLiveData<>();
//    private UserRepository userRepository;

    public LiveData<User> getProfile() {
        if (profileLiveData.getValue() == null) {
//            profileLiveData = profileRepository.getAllFriends();
            profileLiveData.setValue(new User("User3", "username3"));
        }

        return profileLiveData;
    }

    public void updateProfile(User user) {
        profileLiveData.setValue(user);
    }
}

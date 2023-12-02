package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.AddUserService;
import com.example.flashcards_app.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserRepository {

    private AddUserService addUserService;


    public AddUserRepository() {
//        addUserService = RetrofitClient.getRetrofitInstance("").create(AddUserService.class);
    }

    public MutableLiveData<List<User>> getNewFriendsToAdd() {
        MutableLiveData<List<User>> friendsAddLiveData = new MutableLiveData<>();

        Call<List<User>> call = addUserService.getAllFriends();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    friendsAddLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        return friendsAddLiveData;

    }


}

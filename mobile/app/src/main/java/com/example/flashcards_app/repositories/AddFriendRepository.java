package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.AddingFriendsService;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFriendRepository {

    private AddingFriendsService addingFriendsService;


    public AddFriendRepository() {
        addingFriendsService = RetrofitClient.getRetrofitInstance().create(AddingFriendsService.class);
    }

    public MutableLiveData<List<User>> getNewFriendsToAdd() {
        MutableLiveData<List<User>> friendsAddLiveData = new MutableLiveData<>();

        Call<List<User>> call = addingFriendsService.getAllFriends();
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

package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.FriendService;
import com.example.flashcards_app.dto.SendFriendRequestDTO;
import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRepository {
    private FriendService friendService;

    public FriendRepository() {
        friendService = RetrofitClient.getRetrofitInstance().create(FriendService.class);
    }

    public MutableLiveData<List<Friend>> getAllFriends() {
        MutableLiveData<List<Friend>> friendsLiveData = new MutableLiveData<>();

        Call<List<Friend>> call = friendService.getAllFriends();
        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    friendsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {

            }
        });

        return friendsLiveData;
    }

    public MutableLiveData<Boolean> deleteFriend(int friendId) {
        MutableLiveData<Boolean> friendDeleted = new MutableLiveData<>();

        Call<Void> call = friendService.deleteFriend(String.valueOf(friendId));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    friendDeleted.setValue(true);
                }
                else {
                    friendDeleted.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });

        return friendDeleted;
    }
}

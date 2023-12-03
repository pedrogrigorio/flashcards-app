package com.example.flashcards_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.FriendService;
import com.example.flashcards_app.models.Friend;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRepository {
    private FriendService friendService;

    public FriendRepository() {
//        friendService = RetrofitClient.getRetrofitInstance("").create(FriendService.class);
    }

    public MutableLiveData<List<Friend>> getAllFriends() {
        MutableLiveData<List<Friend>> friendsLiveData = new MutableLiveData<>();

        Call<List<Friend>> call = friendService.getAllFriends();
        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("PRINT: requisition OK");
                    friendsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {

            }
        });

        return friendsLiveData;
    }
}

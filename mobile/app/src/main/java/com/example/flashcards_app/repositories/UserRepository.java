package com.example.flashcards_app.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.UserService;
import com.example.flashcards_app.dto.LoginDTO;
import com.example.flashcards_app.dto.RegisterDTO;
import com.example.flashcards_app.dto.UpdateProfileDTO;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.models.UserAuth;
import com.example.flashcards_app.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private UserService userService;

    public UserRepository() {
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);
    }

    public MutableLiveData<User> getUser(String userId) {
        MutableLiveData<User> userLiveData = new MutableLiveData<>();

        Call<User> call = userService.getUser(userId);
        executeAsync(call, userLiveData, null);

        return userLiveData;
    }

    public MutableLiveData<Boolean> register(String username, String email, String password) {
        MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();

        RegisterDTO registerDTO = new RegisterDTO(username, email, password);
        Call<Void> call = userService.register(registerDTO);

        executeAsync(call, null, response -> {
            if (response != null && response.isSuccessful()) {
                registerSuccess.setValue(true);
            } else {
                registerSuccess.setValue(false);
            }
        });

        return registerSuccess;
    }

    public MutableLiveData<UserAuth> login(String email, String password) {
        MutableLiveData<UserAuth> authLiveData = new MutableLiveData<>();

        LoginDTO loginDTO = new LoginDTO(email, password);

        Call<UserAuth> call = userService.login(loginDTO);
        executeAsync(call, authLiveData, null);

        return authLiveData;
    }

    public MutableLiveData<User> updateProfile(String userId, String name) {
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO(name);

        Call<User> call = userService.updateProfile(userId, updateProfileDTO);

        executeAsync(call, userLiveData, null);
        return userLiveData;
    }

    private <T> void executeAsync(Call<T> call, MutableLiveData<T> liveData, ResponseCallback<T> responseCallback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (responseCallback != null) {
                    responseCallback.onResponseReceived(response);
                }
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                // Tratamento de falhas comuns
                t.printStackTrace();
            }
        });
    }

    interface ResponseCallback<T> {
        void onResponseReceived(Response<T> response);
    }
}

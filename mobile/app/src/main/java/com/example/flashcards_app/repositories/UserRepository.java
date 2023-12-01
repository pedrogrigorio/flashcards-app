package com.example.flashcards_app.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.UserService;
import com.example.flashcards_app.dto.LoginDTO;
import com.example.flashcards_app.dto.RegisterDTO;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private UserService userService;

    public UserRepository() {
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);
    }

    public MutableLiveData<User> getProfile() {
        MutableLiveData<User> profileLiveData = new MutableLiveData<>();

        Call<User> call = userService.getProfile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("PRINT: requisition OK");
                    profileLiveData.setValue(response.body());
                } else {
                    // error treatment
                    System.out.println("PRINT: getAllDecks error");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("PRINT: requisition fail " + t.getMessage());
                t.printStackTrace();
            }
        });

        return profileLiveData;
    }

    public MutableLiveData<Boolean> register(String username, String email, String password) {
        RegisterDTO registerDTO = new RegisterDTO(username, email, password);
        Call<Void> call = userService.register(registerDTO);

        MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    registerSuccess.setValue(true);
                } else {
                    registerSuccess.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerSuccess.setValue(false); // Falha na requisição
                t.printStackTrace();
            }
        });

        return registerSuccess;
    }

    public MutableLiveData<String> login(String email, String password) {
        MutableLiveData<String> tokenLiveData = new MutableLiveData<>();

        LoginDTO loginDTO = new LoginDTO(email, password);
        Call<String> call = userService.login(loginDTO);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tokenLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        return tokenLiveData;
    }
}

package com.example.flashcards_app.repositories;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.example.flashcards_app.api.UserService;
import com.example.flashcards_app.dto.LoginDTO;
import com.example.flashcards_app.dto.RegisterDTO;
import com.example.flashcards_app.dto.SearchUsersDTO;
import com.example.flashcards_app.dto.UpdateProfileDTO;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.models.UserAuth;
import com.example.flashcards_app.network.RetrofitClient;
import com.example.flashcards_app.util.AppPreferences;
import com.example.flashcards_app.util.RealPathUtil;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    public MutableLiveData<User> updateName(String userId, String name) {
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO(name);

        Call<User> call = userService.updateName(userId, updateProfileDTO);

        executeAsync(call, userLiveData, null);
        return userLiveData;
    }

    public void updateProfile(Context context, String name, Uri newImgUri, UpdateProfileCallback callback) {
        //TODO: Separate responsibilities later

        String userId = AppPreferences.getUserId();
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        MultipartBody.Part filePart = null;
        RequestBody namePart = null;

        if (newImgUri != null) {
            String path = RealPathUtil.getRealPath(context, newImgUri);

            File file = new File(path);

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            namePart = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        }

        if (filePart == null) {
            UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO(name);
            Call<User> call = userService.updateName(userId, updateProfileDTO);
            executeAsync(call, userLiveData, response -> {
                userLiveData.setValue(response.body());
                callback.onUpdateSuccess(userLiveData.getValue());
            });
        } else {
            Call<User> call = userService.updateProfile(userId, filePart, namePart);

            executeAsync(call, userLiveData, response -> {
                userLiveData.setValue(response.body());
                callback.onUpdateSuccess(userLiveData.getValue());
            });
        }

    }

    public MutableLiveData<List<User>> searchUsers(String query) {
        MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
        SearchUsersDTO searchUsersDTO = new SearchUsersDTO(query);

        Call<List<User>> call = userService.searchUsers(searchUsersDTO);

        executeAsync(call, usersLiveData, null);
        return usersLiveData;
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
                t.printStackTrace();
            }
        });
    }

    interface ResponseCallback<T> {
        void onResponseReceived(Response<T> response);
    }

    public interface UpdateProfileCallback {
        void onUpdateSuccess(User updatedUser);
        void onUpdateFailure(String errorMessage);
    }
}

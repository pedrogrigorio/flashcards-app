package com.example.flashcards_app.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.flashcards_app.util.AppPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    public AuthInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String authToken = AppPreferences.getAccessToken();
        System.out.println("TESTE232323: " + authToken);

        Request newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
        return chain.proceed(newRequest);
    }
}

package com.example.flashcards_app.network;

import com.example.flashcards_app.network.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:3000/";

    public static Retrofit getRetrofitInstance() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }
}

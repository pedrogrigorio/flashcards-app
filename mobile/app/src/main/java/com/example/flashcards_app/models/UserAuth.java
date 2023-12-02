package com.example.flashcards_app.models;

public class UserAuth {

    private String token;
    private String userId;

    public UserAuth(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}

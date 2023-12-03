package com.example.flashcards_app.dto;

public class RegisterDTO {
    private String username;
    private String email;
    private String password;

    public RegisterDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

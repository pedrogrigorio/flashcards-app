package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.flashcards_app.R;

public class RegisterActivity extends AppCompatActivity {

    Button signup;
    Button signin;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.btn_signin);
        back = findViewById(R.id.btn_back);

        back.setOnClickListener(v -> {
            accessMainActivity();
        });

        signup.setOnClickListener(v -> {
            accessLoginScreen();
        });

        signin.setOnClickListener(v -> {
            accessLoginScreen();
        });
    }

    private void accessLoginScreen() {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }

    private void accessMainActivity() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
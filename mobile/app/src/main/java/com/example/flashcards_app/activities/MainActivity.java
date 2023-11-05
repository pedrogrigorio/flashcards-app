package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.example.flashcards_app.R;

public class MainActivity extends AppCompatActivity {

    Button signup;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.btn_signing);

        signup.setOnClickListener(v -> {
            accessRegisterScreen();
        });

        signin.setOnClickListener(v -> {
            accessLoginScreen();
        });
    }

    private void accessRegisterScreen() {
        Intent in = new Intent(this, RegisterActivity.class);
        startActivity(in);
    }

    private void accessLoginScreen() {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }
}
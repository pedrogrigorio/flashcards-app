package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.example.flashcards_app.R;
import com.example.flashcards_app.util.AppPreferences;

public class MainActivity extends AppCompatActivity {

    Button signup;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        AppPreferences.setup(this);

        String token = AppPreferences.getAccessToken();
        if (token != null && !token.isEmpty()) {
            accessHomeActivity();
        }

        initViews();

        setupInitialConfig();
    }

    private void initViews() {
        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.btn_signing);

    }

    private void setupInitialConfig() {
        signup.setOnClickListener(v -> {
            Intent in = new Intent(this, RegisterActivity.class);
            startActivity(in);
        });

        signin.setOnClickListener(v -> {
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
        });
    }

    private void accessHomeActivity() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }
}
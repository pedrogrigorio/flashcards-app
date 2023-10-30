package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    Button signup;
    Button signin;
    ImageView back;
    TextInputLayout username;
    TextInputLayout email;
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.btn_signin);
        back = findViewById(R.id.btn_back);
        username = findViewById(R.id.username_field);
        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);

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
        Toast.makeText(this, username.getEditText().getText() + " " + email.getEditText().getText() + " " + password.getEditText().getText(), Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }

    private void accessMainActivity() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
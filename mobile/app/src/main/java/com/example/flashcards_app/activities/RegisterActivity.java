package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button signup;
    Button signing;
    ImageView back;
    TextInputLayout usernameLayout;
    TextInputEditText usernameEditText;
    TextInputLayout emailLayout;
    TextInputEditText emailEditText;
    TextInputLayout passwordLayout;
    TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = findViewById(R.id.btn_signup);
        signing = findViewById(R.id.btn_signing);
        back = findViewById(R.id.btn_back);
        usernameLayout = findViewById(R.id.username_field);
        usernameEditText = findViewById(R.id.username_editText);
        emailLayout = findViewById(R.id.email_field);
        emailEditText = findViewById(R.id.email_editText);
        passwordLayout = findViewById(R.id.password_field);
        passwordEditText = findViewById(R.id.password_editText);

        signup.setEnabled(false);

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = s.toString();
                boolean isValid = true;

                if (password.length() >= 5) {
                    isValid = password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).+$");
                } else {
                    passwordLayout.setHelperText("Sua senha deve ter pelo menos 5 caracteres");
                }

                signup.setEnabled(isValid);

                if (!isValid) {
                    passwordLayout.setError("Sua senha deve conter letras, números e caracteres especiais.");
                } else {
                    passwordLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        back.setOnClickListener(v -> {
            accessMainActivity();
        });

        signup.setOnClickListener(v -> {
            accessLoginScreen();
        });

        signing.setOnClickListener(v -> {
            accessLoginScreen();
        });
    }

    private void accessLoginScreen() {
        Toast.makeText(this, usernameLayout.getEditText().getText() + " " + emailLayout.getEditText().getText() + " " + passwordEditText.getText(), Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }

    private void accessMainActivity() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
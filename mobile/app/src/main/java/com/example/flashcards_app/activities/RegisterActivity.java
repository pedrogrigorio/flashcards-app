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
    TextInputLayout username;
    TextInputLayout email;
    TextInputLayout layoutPassword;
    TextInputEditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = findViewById(R.id.btn_signup);
        signing = findViewById(R.id.btn_signing);
        back = findViewById(R.id.btn_back);
        username = findViewById(R.id.username_field);
        email = findViewById(R.id.email_field);
        layoutPassword = findViewById(R.id.password_field);
        editTextPassword = findViewById(R.id.eTextPassword);

        signup.setEnabled(false);
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = s.toString();
                if (password.length() >= 5) {
                    Pattern pattern = Pattern.compile("[!@#$%&*()]");
                    Matcher matcher = pattern.matcher(password);
                    boolean isPasswordContainsSpecialChar = matcher.find();

                    if (isPasswordContainsSpecialChar) {
                        layoutPassword.setHelperText("Senha forte");
                    }
                    else {
                        layoutPassword.setError("Sua senha deve conter pelo menos 1 caracter especial. (ex: !@#$%&*())");
                        signup.setEnabled(false);
                    }
                }
                else {
                    layoutPassword.setHelperText("Sua senha deve ter pelo menos 5 caracteres");
                    signup.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();
                Pattern pattern = Pattern.compile("[!@#$%&*()]");
                Matcher matcher = pattern.matcher(password);
                boolean isPasswordContainsSpecialChar = matcher.find();

                if (password.length() >= 5 && isPasswordContainsSpecialChar) {
                    signup.setEnabled(true);
                }
                else {
                    signup.setEnabled(false);
                }
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
        Toast.makeText(this, username.getEditText().getText() + " " + email.getEditText().getText() + " " + editTextPassword.getText(), Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }

    private void accessMainActivity() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
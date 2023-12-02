package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.util.AppPreferences;
import com.example.flashcards_app.viewmodel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    Context context;

    Button signup;
    Button signing;
    ImageButton back;
    TextInputLayout email;
    TextInputEditText emailEditText;
    TextInputLayout password;
    TextInputEditText passwordEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        context = this;
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        initViews();

        setupInitialConfig();
    }

    private void initViews() {
        signup = findViewById(R.id.btn_signup);
        signing = findViewById(R.id.btn_signing);
        back = findViewById(R.id.btn_back);
        email = findViewById(R.id.username_field);
        emailEditText = findViewById(R.id.email_editText);
        password = findViewById(R.id.password_field);
        passwordEditText = findViewById(R.id.password_editText);

    }

    private void setupInitialConfig() {
        back.setOnClickListener(v -> {
            accessMainActivity();
        });

        signup.setOnClickListener(v -> {
            accessRegisterActivity();
        });

        signing.setOnClickListener(v -> {
            login();
        });
    }

    private void accessRegisterActivity() {
        Intent in = new Intent(this, RegisterActivity.class);
        startActivity(in);
    }

    private void accessMainActivity() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    private void accessHomeActivity() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }

    private void login() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        loginViewModel.login(email, password).observe(this, authData  -> {
            if (authData != null) {
                AppPreferences.setAccessToken(authData.getToken());
                AppPreferences.setUserId(authData.getUserId());

                String savedToken = AppPreferences.getAccessToken();
                if (!savedToken.isEmpty()) {
                    accessHomeActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Erro ao salvar o token", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Erro ao obter o token", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
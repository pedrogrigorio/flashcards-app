package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.viewmodel.LoginViewModel;
import com.example.flashcards_app.viewmodel.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences("MyPrefs", this.MODE_PRIVATE);
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
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        });

        signup.setOnClickListener(v -> {
            Intent in = new Intent(this, RegisterActivity.class);
            startActivity(in);
        });

        signing.setOnClickListener(v -> {
            setupViewModel();
        });
    }

    private void accessHomeActivity() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }

    private void accessChooseNameActivity() {
        Intent in = new Intent(this, ChooseNameActivity.class);
        startActivity(in);
    }

    private void setupViewModel() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        loginViewModel.login(email, password).observe(this, token  -> {
            if (token != null && !token.isEmpty()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", token);

                editor.apply();

                String savedToken = sharedPreferences.getString("token", "");
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
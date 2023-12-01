package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.ProfileViewModel;
import com.example.flashcards_app.viewmodel.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    RegisterViewModel registerViewModel;

    Button signup;
    Button signing;
    ImageButton back;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

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

        final boolean[] usernameIsValid = {false};
        final boolean[] passwordIsValid = {false};
        final boolean[] emailIsValid = {false};

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = s.toString();

                if (username.length() >= 5) {
                    usernameIsValid[0] = username.matches("^[^\\s]+$");
                    usernameLayout.setHelperText(null);
                } else {
                    usernameIsValid[0] = false;
                    usernameLayout.setHelperText("Nome de usuário deve ter pelo menos 5 caracteres");
                }

                boolean isValid = usernameIsValid[0] && passwordIsValid[0] && emailIsValid[0];
                signup.setEnabled(isValid);

                boolean containsSpace = username.matches(".*\\s+.*");
                if (containsSpace) {
                    usernameLayout.setError("Nome de usuário não pode conter espaços em branco.");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                emailIsValid[0] = Patterns.EMAIL_ADDRESS.matcher(email).matches();

                if (!emailIsValid[0]) {
                    emailLayout.setError("Insira um endereço de e-mail inválido.");
                } else {
                    emailLayout.setHelperText(null);
                    emailLayout.setError(null);
                }

                boolean isValid = usernameIsValid[0] && passwordIsValid[0] && emailIsValid[0];
                signup.setEnabled(isValid);
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

                if (password.length() >= 5) {
                    passwordIsValid[0] = password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).+$");
                    passwordLayout.setHelperText(null);
                } else {
                    passwordLayout.setHelperText("Sua senha deve ter pelo menos 5 caracteres");
                    passwordIsValid[0] = false;
                }

                boolean isValid = usernameIsValid[0] && passwordIsValid[0] && emailIsValid[0];
                signup.setEnabled(isValid);

                if (!passwordIsValid[0]) {
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
            configRegisterViewModel();
        });

        signing.setOnClickListener(v -> {
            accessLoginScreen();
        });
    }

    private void accessLoginScreen() {
        Toast.makeText(this, usernameEditText.getText() + " " + emailEditText.getText() + " " + passwordEditText.getText(), Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }

    private void accessMainActivity() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    private void configRegisterViewModel() {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        registerViewModel.register(username, email, password).observe(this, isSuccess  -> {
            if (isSuccess) {
                Toast.makeText(this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha no cadastro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
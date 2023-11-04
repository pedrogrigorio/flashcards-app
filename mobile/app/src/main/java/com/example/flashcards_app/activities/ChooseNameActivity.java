package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.HomeActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChooseNameActivity extends AppCompatActivity {

    TextInputLayout nameLayout;
    TextInputEditText nameEditText;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_name);

        nameLayout = findViewById(R.id.name_field);
        nameEditText = findViewById(R.id.name_editText);
        continueButton = findViewById(R.id.btn_continue);
        continueButton.setEnabled(false);

        continueButton.setOnClickListener(v -> {
            accessHomeActivity();
        });

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString();
                boolean isValid;

                if (name.length() >= 2) {
                    isValid = name.matches("^[a-zA-Z][a-zA-Z\\s]*[a-zA-Z]$");
                } else {
                    nameLayout.setHelperText("Nome deve ter pelo menos 2 letras e pode conter apenas letras e espaços.");
                    isValid = false;
                }

                continueButton.setEnabled(isValid);

                boolean startsWithLetters = name.matches("^[a-zA-Z].*");
                boolean containsOnlyLettersAndSpaces = name.matches("^[a-zA-Z\\s]*$");
                boolean endsWithLetters = name.matches(".*[a-zA-Z]$");

                if (!isValid) {
                    if (!startsWithLetters) {
                        nameLayout.setError("Nome deve iniciar com letras.");
                    } else if (!containsOnlyLettersAndSpaces) {
                        nameLayout.setError("Nome pode conter apenas letras e espaços.");
                    } else if (!endsWithLetters) {
                        nameLayout.setError("Nome não pode terminar com espaços em branco.");
                    }
                } else {
                    nameLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void accessHomeActivity() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }
}
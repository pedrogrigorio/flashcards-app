package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.flashcards_app.R;

public class SettingsActivity extends AppCompatActivity {

    RelativeLayout disconnect;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        disconnect = findViewById(R.id.disconnect);
        back = findViewById(R.id.back);

        disconnect.setOnClickListener(v -> {
            // TODO: Clear SharedPreferences
            accessInitialScreen();
        });

        back.setOnClickListener(v -> {
            accessHomeScreen();
        });
    }

    private void accessHomeScreen() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }

    private void accessInitialScreen() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
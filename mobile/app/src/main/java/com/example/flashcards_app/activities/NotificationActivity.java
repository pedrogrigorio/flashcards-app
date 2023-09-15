package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.flashcards_app.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void acessMainScreen(View view) {
        Intent in = new Intent(NotificationActivity.this, MainActivity.class);
        startActivity(in);
    }
}
package com.example.flashcards_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void acessPerfil(View view) {
        Intent in = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(in);
    }

    public void acessNotifications(View view) {
        Intent in = new Intent(MainActivity.this, NotificationActivity.class);
        startActivity(in);
    }
}
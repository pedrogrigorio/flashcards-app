package com.example.flashcards_app.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.flashcards_app.R;



public class FinishedReviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_review);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button finishedReview = findViewById(R.id.finished_review_button);
        Intent intent = new Intent(this, HomeActivity.class);

        finishedReview.setOnClickListener(view -> {
            startActivity(intent);
            finish();
        });

    }
}

package com.example.flashcards_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.flashcards_app.R;

public class FinishedReviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button finishedReview = findViewById(R.id.finished_review_button);
        Intent intent = new Intent(this, HomeActivity.class);

        finishedReview.setOnClickListener(view -> {
            startActivity(intent);
            finish();
        });

    }
}

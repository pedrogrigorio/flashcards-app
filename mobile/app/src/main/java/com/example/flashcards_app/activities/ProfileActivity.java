package com.example.flashcards_app.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.dialogs.EditDeckDialog;
import com.example.flashcards_app.dialogs.EditNameDialog;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.FriendViewModel;
import com.example.flashcards_app.viewmodel.ProfileViewModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    User user;
    ProfileViewModel profileViewModel;

    ImageView profileImg;
    TextView name;
    TextView username;
    TextView dayStreak;
    TextView reviewedCardsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        profileImg = findViewById(R.id.profile_img);
        name = findViewById(R.id.name_textView);
        username = findViewById(R.id.username_textView);
        dayStreak = findViewById(R.id.day_streak);
        reviewedCardsNumber = findViewById(R.id.cards_reviewed_number);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        configProfileViewModel();

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);

    }

    private void configProfileViewModel() {
        profileViewModel.getProfile().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User updatedProfile) {
                user = updatedProfile;
                updateView();
            }
        });
    }

    private void updateView() {
        name.setText(user.getName());
        username.setText(user.getUsername());
        dayStreak.setText(user.getDayStreak() + "");
        reviewedCardsNumber.setText(user.getCardsReviewed() + "");

        if (!user.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(user.getImgSrc())
                    .into(profileImg);
        }
    }

    public void accessMainScreen(View view) {
        Intent in = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(in);
    }
}
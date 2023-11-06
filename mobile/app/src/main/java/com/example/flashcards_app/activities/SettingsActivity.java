package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.dialogs.EditDeckDialog;
import com.example.flashcards_app.dialogs.EditProfileDialog;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.ProfileViewModel;
import com.squareup.picasso.Picasso;

public class SettingsActivity extends AppCompatActivity {

    User user;
    ProfileViewModel profileViewModel;

    ImageView profileImg;
    TextView name;
    TextView username;

    LinearLayout disconnect;
    ImageButton back;
    Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        profileImg = findViewById(R.id.profile_img);
        name = findViewById(R.id.name_textView);
        username = findViewById(R.id.username_textView);
        disconnect = findViewById(R.id.btn_logout);
        back = findViewById(R.id.btn_back);
        editProfile = findViewById(R.id.btn_edit_profile);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        configProfileViewModel();

        disconnect.setOnClickListener(v -> {
            // TODO: Clear SharedPreferences
            accessInitialScreen();
        });

        back.setOnClickListener(v -> {
            accessHomeScreen();
        });

        editProfile.setOnClickListener(v -> {
            EditProfileDialog dialog = new EditProfileDialog(user);
            dialog.setDialogResult(new EditProfileDialog.onDialogResult() {
                @Override
                public void finish(User updatedProfile) {
                    profileViewModel.updateProfile(updatedProfile);
                }
            });
            dialog.show(getSupportFragmentManager(), "edit_profile_popup");
        });
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

        if (!user.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(user.getImgSrc())
                    .into(profileImg);
        }
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
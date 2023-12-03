package com.example.flashcards_app.activities;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.dialogs.EditDeckDialog;
import com.example.flashcards_app.dialogs.EditProfileDialog;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.util.AppPreferences;
import com.example.flashcards_app.viewmodel.ProfileViewModel;
import com.example.flashcards_app.viewmodel.SettingsViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class SettingsActivity extends AppCompatActivity {

    User user;
    SettingsViewModel settingsViewModel;

    ImageView profileImg;
    TextView name;
    TextView username;

    LinearLayout logout;
    ImageButton back;
    Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        initViews();

        setupInitialConfig();

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        fetchData();
    }

    private void initViews() {
        profileImg = findViewById(R.id.profile_img);
        name = findViewById(R.id.name_textView);
        username = findViewById(R.id.username_textView);
        logout = findViewById(R.id.btn_logout);
        back = findViewById(R.id.btn_back);
        editProfile = findViewById(R.id.btn_edit_profile);
    }

    /* Load buttons onClickListener */
    private void setupInitialConfig() {
        logout.setOnClickListener(v -> {
            AppPreferences.cleanUserSession();
            accessMainActivity();
        });

        back.setOnClickListener(v -> {
            accessHomeActivity();
        });

        editProfile.setOnClickListener(v -> {
            EditProfileDialog dialog = new EditProfileDialog(user);
            dialog.setDialogResult(new EditProfileDialog.onDialogResult() {
                @Override
                public void finish(User updatedProfile) {
                    settingsViewModel.updateProfile(updatedProfile);
                }
            });
            dialog.show(getSupportFragmentManager(), "edit_profile_popup");
        });
    }

    private void fetchData() {
        String userId = AppPreferences.getUserId();
        settingsViewModel.getProfile(userId).observe(this, updatedProfile -> {
            user = updatedProfile;
            updateView();
        });
    }

    private void updateView() {
        name.setText(user.getName());
        username.setText(user.getUsername());

        if (user.getImgSrc() != null && !user.getImgSrc().isEmpty()) {
            String imageUrl = "http://10.0.2.2:3000/image/" + user.getImgSrc();

            Picasso.get()
                    .load(imageUrl)
                    .into(profileImg);
        }
    }

    private void accessHomeActivity() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }

    private void accessMainActivity() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
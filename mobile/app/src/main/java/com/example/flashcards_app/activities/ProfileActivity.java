package com.example.flashcards_app.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

public class ProfileActivity extends AppCompatActivity {

    User user;
    ImageView profilePhoto;
    FloatingActionButton camButton;
    ImageView editNameButton;
    TextView name;
    TextView username;

    ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePhoto = findViewById(R.id.profile_img);
        camButton = findViewById(R.id.btn_cam);
        editNameButton = findViewById(R.id.btn_edit_name);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);

        user = new User("", "");

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        configProfileViewModel();

        editNameButton.setOnClickListener(v -> {
            EditNameDialog dialog = new EditNameDialog(user);
            dialog.setDialogResult(new EditNameDialog.onDialogResult() {
                @Override
                public void finish(User updatedProfile) {
                    profileViewModel.updateProfile(updatedProfile);
                }
            });
            dialog.show(getSupportFragmentManager(), "edit_name_popup");
        });

        camButton.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop(1f, 1f)
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start();
        });

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profilePhoto.setImageURI(uri);
    }

    public void accessMainScreen(View view) {
        Intent in = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(in);
    }

    private void configProfileViewModel() {
        profileViewModel.getProfile().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User updatedProfile) {
                user = updatedProfile;
                name.setText(user.getName());
                username.setText(user.getUsername());
            }
        });
    }
}
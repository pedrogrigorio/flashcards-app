package com.example.flashcards_app.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.flashcards_app.fragments.DecksFragment;
import com.example.flashcards_app.fragments.FriendsFragment;
import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.ViewPagerAdapter;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.HomeViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    User user;

    ViewPager2 viewPager;
    TabLayout tabLayout;
    Button createDeck;
    Button addFriends;
    ImageView settings;
    ImageView notifications;
    ImageView profileImg;
    TextView nameTextView;
    TextView usernameTextView;

    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        createDeck = findViewById(R.id.btn_create_deck);
        addFriends = findViewById(R.id.btn_add_friends);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        settings = findViewById(R.id.settings);
        notifications = findViewById(R.id.notifications);
        profileImg = findViewById(R.id.profile_img);
        nameTextView = findViewById(R.id.name_textView);
        usernameTextView = findViewById(R.id.username_textView);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        configHomeViewModel();

        settings.setOnClickListener(v -> {
            accessSettingsScreen();
        });

        profileImg.setOnClickListener(v -> {
            accessProfile();
        });

        notifications.setOnClickListener(v -> {
            accessNotifications();
        });

        configTabLayout();
    }

    private void configTabLayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        adapter.addFragment(new DecksFragment(), "Baralhos");
        adapter.addFragment(new FriendsFragment(), "Amigos");

        viewPager.setOffscreenPageLimit(adapter.getItemCount());
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    addFriends.setVisibility(View.INVISIBLE);
                    createDeck.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    createDeck.setVisibility(View.INVISIBLE);
                    addFriends.setVisibility(View.VISIBLE);
                }
            }
        });

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(adapter.getTitle(position));
        });

        mediator.attach();
    }

    private void configHomeViewModel() {
        homeViewModel.getProfile().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User profile) {
                user = profile;
                updateView();
            }
        });
    }

    private void updateView() {
        nameTextView.setText(user.getName());
        usernameTextView.setText(user.getUsername());

        if (!user.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(user.getImgSrc())
                    .into(profileImg);
        }
    }

    /* Change page methods */

    private void accessProfile() {
        Intent in = new Intent(this, ProfileActivity.class);
        startActivity(in);
    }

    private void accessNotifications() {
        Intent in = new Intent(this, NotificationsActivity.class);
        startActivity(in);
    }

    private void accessSettingsScreen() {
        Intent in = new Intent(this, SettingsActivity.class);
        startActivity(in);
    }

    /* Get view elements */
    public Button getCreateDeckButton() {
        return createDeck;
    }

    public Button getAddFriendsButton() {
        return addFriends;
    }

}
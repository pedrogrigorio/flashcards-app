package com.example.flashcards_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.flashcards_app.fragments.DecksFragment;
import com.example.flashcards_app.fragments.UserFragment;
import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;
    Button createDeck;
    Button addFriends;
    ImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createDeck = findViewById(R.id.btn_create_deck);
        addFriends = findViewById(R.id.btn_add_friends);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        profileImg = findViewById(R.id.profile_img);

        profileImg.setOnClickListener(v -> {
            accessProfile();
        });

        configTabLayout();
    }

    private void configTabLayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        adapter.addFragment(new DecksFragment(), "Baralhos");
        adapter.addFragment(new UserFragment(), "Amigos");

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

    /* Change page methods */

    public void accessProfile() {
        Intent in = new Intent(this, ProfileActivity.class);
        startActivity(in);
    }

    public void accessNotifications(View v) {
        Intent in = new Intent(this, NotificationActivity.class);
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
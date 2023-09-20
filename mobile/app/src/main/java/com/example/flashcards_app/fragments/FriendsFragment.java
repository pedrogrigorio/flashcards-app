package com.example.flashcards_app.fragments;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.MainActivity;
import com.example.flashcards_app.models.Deck;

public class FriendsFragment extends Fragment {

    LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        linearLayout = view.findViewById(R.id.friendsLinearLayout);

        Button addButton = mainActivity.getAddFriendsButton();
        addButton.setOnClickListener(v -> {
            addFriends();
        });

        return view;
    }

    public void addFriends() {
        View cardView = LayoutInflater.from(requireContext()).inflate(R.layout.item_friend_card, null);

        // Layout config
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        Resources r = getContext().getResources();
        int margin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                24,
                r.getDisplayMetrics()
        );

        layoutParams.setMargins(margin, 0, margin, margin);
        cardView.setLayoutParams(layoutParams);

        /* Create object */
        linearLayout.addView(cardView);
    };
}
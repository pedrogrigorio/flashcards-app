package com.example.flashcards_app.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.MainActivity;
import com.example.flashcards_app.adapters.DeckAdapter;
import com.example.flashcards_app.adapters.FriendAdapter;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.viewmodel.DeckViewModel;
import com.example.flashcards_app.viewmodel.FriendViewModel;

import java.util.List;

public class FriendsFragment extends Fragment {

    private FriendViewModel friendViewModel;
    private RecyclerView recyclerView;
    private FriendAdapter adapter;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        context = getActivity();

        adapter = new FriendAdapter();
        configAdapter();

        recyclerView = view.findViewById(R.id.friends_recycler_view);
        configRecyclerView();

        friendViewModel = new ViewModelProvider(this).get(FriendViewModel.class);
        configFriendViewModel();

        Button addButton = mainActivity.getAddFriendsButton();
        addButton.setOnClickListener(v -> {
            friendViewModel.insertFriend(new Friend(0, "User", "user"));
        });

        return view;
    }

    private void configAdapter() {
        // implement configAdapter
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void configFriendViewModel() {
        friendViewModel.getFriends().observe(getActivity(), new Observer<List<Friend>>() {
            @Override
            public void onChanged(List<Friend> friends) {
                adapter.setFriends(friends);
            }
        });
    }
}
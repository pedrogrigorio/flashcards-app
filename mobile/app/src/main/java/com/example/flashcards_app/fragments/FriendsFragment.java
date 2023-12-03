package com.example.flashcards_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.AddUserActivity;
import com.example.flashcards_app.activities.HomeActivity;
import com.example.flashcards_app.adapters.DeckAdapter;
import com.example.flashcards_app.adapters.FriendAdapter;
import com.example.flashcards_app.dialogs.DeleteFriendDialog;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Friend;
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

        HomeActivity homeActivity = (HomeActivity) getActivity();

        initViews(view, homeActivity);

        setupInitialConfig();

        configAdapter();

        configRecyclerView();

        getAllFriends();

        return view;
    }

    private void initViews(View view, HomeActivity homeActivity) {
        recyclerView = view.findViewById(R.id.friends_recycler_view);
        Button addButton = homeActivity.getAddFriendsButton();

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddUserActivity.class);
            startActivity(intent);
        });
    }

    private void setupInitialConfig() {
        adapter = new FriendAdapter();
        friendViewModel = new ViewModelProvider(this).get(FriendViewModel.class);
    }

    private void configAdapter() {
        adapter.setDeleteFriendListener(new FriendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Friend friend, int position) {
                DeleteFriendDialog dialog = new DeleteFriendDialog(friend.getName(), friend.getUserId());
                dialog.setDialogResult(new DeleteFriendDialog.onDialogResult() {
                    @Override
                    public void finish() {
                        friendViewModel.deleteFriend(friend).observe(getActivity(), friendDeleted -> {
                            adapter.deleteFriend(friend.getUserId());
                        });
                    }
                });
                dialog.show(getChildFragmentManager(), "delete friend popup");
            }
        });
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void getAllFriends() {
        friendViewModel.getAllFriends().observe(getActivity(), friends -> {
            adapter.setFriends(friends);
        });
    }
}
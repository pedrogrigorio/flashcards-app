package com.example.flashcards_app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.AddFriendAdapter;
import com.example.flashcards_app.viewmodel.AddFriendViewModel;

public class AddingFriendsActivity extends AppCompatActivity {

    private AddFriendViewModel addFriendViewModel;
    private RecyclerView recyclerView;
    private AddFriendAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_user_friends);

        adapter = new AddFriendAdapter();



    }


    private void ConfigRecyclerView() {

    }

}




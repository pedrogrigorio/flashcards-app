package com.example.flashcards_app.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.AddFriendAdapter;
import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.AddFriendViewModel;

import java.util.List;

public class AddingFriendsActivity extends AppCompatActivity {

    private AddFriendViewModel addFriendViewModel;
    private RecyclerView recyclerView;
    private AddFriendAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);



        adapter = new AddFriendAdapter();

        recyclerView = findViewById(R.id.users_recycle_view);
        ConfigRecyclerView();
        addFriendViewModel = new ViewModelProvider(this).get(AddFriendViewModel.class);
        configUserViewModel();

        Button addButton = findViewById(R.id.add_user_button);
        addButton.setOnClickListener(v -> {
            addFriendViewModel.insertFriend(new User("User", "User@"));
        });

    }


    private void ConfigRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void configUserViewModel() {
        addFriendViewModel.getAddFriends().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
            }
        });


    }

//    private void configAdapter() {
//    ainda por implementar
//    }

}




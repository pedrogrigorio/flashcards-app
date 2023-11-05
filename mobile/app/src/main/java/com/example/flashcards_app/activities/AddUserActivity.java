package com.example.flashcards_app.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.AddUserAdapter;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.AddUserViewModel;

import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    private AddUserViewModel addUserViewModel;
    private RecyclerView recyclerView;
    private AddUserAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        adapter = new AddUserAdapter();

        recyclerView = findViewById(R.id.users_recycle_view);
        ConfigRecyclerView();
        addUserViewModel = new ViewModelProvider(this).get(AddUserViewModel.class);
        configUserViewModel();

        Button addButton = findViewById(R.id.add_user_button);
        addButton.setOnClickListener(v -> {
            addUserViewModel.insertFriend(new User("User", "User@"));
        });

    }


    private void ConfigRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void configUserViewModel() {
        addUserViewModel.getAddFriends().observe(this, new Observer<List<User>>() {
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




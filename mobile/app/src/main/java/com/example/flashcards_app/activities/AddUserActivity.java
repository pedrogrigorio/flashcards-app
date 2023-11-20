package com.example.flashcards_app.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.AddUserAdapter;
import com.example.flashcards_app.adapters.FriendAdapter;
import com.example.flashcards_app.dialogs.DeleteFriendDialog;
import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.AddUserViewModel;
import com.example.flashcards_app.viewmodel.NotificationViewModel;

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
        configAdapter();

        addUserViewModel = new ViewModelProvider(this).get(AddUserViewModel.class);
        configUserViewModel();

        Button addButton = findViewById(R.id.add_user_button);
        ImageButton backButtonAction = findViewById(R.id.back_button_users);

        addButton.setOnClickListener(v -> {
            addUserViewModel.insertFriend(new User("User", "User@"));
        });

        backButtonAction.setOnClickListener(v -> {
            backButton();
        });


        SearchView searchView = findViewById(R.id.user_search);

        searchView.setOnClickListener(v -> {
            searchView.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT);
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                addUserViewModel.getSearchRequest(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
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


    private void backButton() {
        finish();
    }

    private void configAdapter() {
        adapter.setDeleteFriendListener(new AddUserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user, int position) {
                Friend friend = new Friend(0, user.getName(), user.getUsername());
                DeleteFriendDialog dialog = new DeleteFriendDialog(friend);
                dialog.setDialogResult(new DeleteFriendDialog.onDialogResult() {
                    @Override
                    public void finish() {

                        User user = addUserViewModel.getUser(position);
                        addUserViewModel.deleteFriend(user, position);
                    }
                });
                dialog.show(getSupportFragmentManager(), "delete friend");
            }
        });
    }

}




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
    private ImageButton backButtonAction;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        initViews();
        setupInitialConfig();

        configRecyclerView();

        configAdapter();

        addUserViewModel = new ViewModelProvider(this).get(AddUserViewModel.class);
    }

    /* Load views */
    private void initViews() {
        recyclerView = findViewById(R.id.users_recycle_view);
        backButtonAction = findViewById(R.id.btn_back);
        searchView = findViewById(R.id.user_search);
    }

    /* Load buttons onClickListener */
    private void setupInitialConfig() {
        adapter = new AddUserAdapter();

        backButtonAction.setOnClickListener(v -> {
            finish();
        });

        searchView.setOnClickListener(v -> {
            searchView.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchUsers(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public void searchUsers(String query) {
        addUserViewModel.searchUsers(query).observe(this, users -> {
            adapter.setUsers(users);
        });
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void configAdapter() {
        adapter.setAddFriendListener(user -> {
            sendFriendRequest(user);
        });

        adapter.setDeleteFriendListener(user -> {
            DeleteFriendDialog dialog = new DeleteFriendDialog(user.getName(), user.getId());

            dialog.setDialogResult(() -> {
                addUserViewModel.deleteFriend(user);
            });

            dialog.show(getSupportFragmentManager(), "delete friend");
        });
    }

    private void sendFriendRequest(User user) {
        addUserViewModel.sendFriendRequest(user).observe(this, notificationSent -> {
            Toast.makeText(this, "SOLICITAÇÃO ENVIADA", Toast.LENGTH_SHORT).show();
        });
    }

}




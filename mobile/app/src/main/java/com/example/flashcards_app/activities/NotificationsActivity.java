package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.NotificationsAdapter;
import com.example.flashcards_app.dialogs.EditProfileDialog;
import com.example.flashcards_app.models.Notification;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.util.AppPreferences;
import com.example.flashcards_app.viewmodel.NotificationViewModel;

import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private NotificationViewModel notificationViewModel;
    private RecyclerView recyclerView;
    private NotificationsAdapter adapter;

    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        initViews();

        setupInitialConfig();

        configRecyclerView();

        fetchAllNotifications();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.notification_recycle_view);
        back = findViewById(R.id.btn_back);
    }

    private void setupInitialConfig() {
        adapter = new NotificationsAdapter();
        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

        back.setOnClickListener(v -> {
            accessHomeActivity();
        });
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setAcceptButtonListener(notification -> {
            notificationViewModel.acceptFriendRequest(notification).observe(this, updatedNotification -> {
                Toast.makeText(this, "Solicitação aceita", Toast.LENGTH_SHORT).show();
                adapter.updateNotification(updatedNotification);
            });
        });

        adapter.setRejectButtonListener(notification -> {
            notificationViewModel.rejectFriendRequest(notification).observe(this, updatedNotification -> {
                Toast.makeText(this, "Solicitação recusada", Toast.LENGTH_SHORT).show();
                adapter.updateNotification(updatedNotification);
            });
        });
    }


    public void fetchAllNotifications() {
        notificationViewModel.getAllNotifications().observe(this, notifications -> {
            adapter.setNotifications(notifications);
        });

    }

    private void accessHomeActivity() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }
}
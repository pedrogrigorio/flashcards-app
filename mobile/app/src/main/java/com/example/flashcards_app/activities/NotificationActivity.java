package com.example.flashcards_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.flashcards_app.R;
import com.example.flashcards_app.adapters.NotificationAdapter;
import com.example.flashcards_app.models.Notification;
import com.example.flashcards_app.viewmodel.NotificationViewModel;

import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private NotificationViewModel notificationViewModel;
    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        notificationAdapter = new NotificationAdapter();

        recyclerView = findViewById(R.id.notification_recycle_view);
        configRecyclerView();

        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        configNotificationViewModel();


        Button addNotification  = findViewById(R.id.add_notification_button);
        back = findViewById(R.id.btn_back);

        back.setOnClickListener(v -> {
            accessHomeActivity();
        });

        addNotification.setOnClickListener(v -> {
            notificationViewModel.insertNotification(new Notification("Test notification", "https://www.haliburtonforest.com/wp-content/uploads/2017/08/placeholder-square.jpg"), this);
        });
    }

    private void configRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(this.notificationAdapter);
    }


    public void configNotificationViewModel() {
        notificationViewModel.getNotification().observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                notificationAdapter.setNotifications(notifications);
            }
        });

    }

    public void backButton() {
        finish();
    }

    private void accessHomeActivity() {
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }
}
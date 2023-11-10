package com.example.flashcards_app.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Notification;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    private List<Notification> notifications = new ArrayList<>();


    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        Notification currentNotification = notifications.get(position);
        holder.TextNotificationName.setText(currentNotification.getNotificationText());
        if (!currentNotification.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(currentNotification.getImgSrc())
                    .into(holder.notificationImage);
        }
    }

    @Override
    public int getItemCount() {
       return notifications.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNotifications(List<Notification> notification){
        this.notifications = notification;
        notifyDataSetChanged();
    }

    static class NotificationHolder extends RecyclerView.ViewHolder {
        private TextView TextNotificationName;
        private ImageView notificationImage;
        public NotificationHolder(@NonNull View itemView) {
            super(itemView);
            notificationImage =  itemView.findViewById(R.id.notification_img);
            TextNotificationName = itemView.findViewById(R.id.TextNotificationName);
        }
    }



}

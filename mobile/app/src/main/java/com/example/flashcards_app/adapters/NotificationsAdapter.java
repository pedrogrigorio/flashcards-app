package com.example.flashcards_app.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Notification;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationHolder> {

    private List<Notification> notifications = new ArrayList<>();
    private OnItemClickListener acceptButtonListener;
    private OnItemClickListener rejectButtonListener;

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        Notification currentNotification = notifications.get(position);
        holder.notificationText.setText(currentNotification.getText());

        if (currentNotification.getImgSrc() != null && !currentNotification.getImgSrc().isEmpty()) {
            String imageUrl = "http://10.0.2.2:3000/image/" + currentNotification.getImgSrc();

            Picasso.get()
                    .load(imageUrl)
                    .into(holder.notificationImage);
        }

        holder.acceptButton.setOnClickListener(v -> {
            acceptButtonListener.onItemClick(currentNotification);
        });

        holder.rejectButton.setOnClickListener(v -> {
            rejectButtonListener.onItemClick(currentNotification);
        });

        if (currentNotification.getStatus() != 0) {
            holder.acceptButton.setVisibility(View.GONE);
            holder.rejectButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
       return notifications.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNotifications(List<Notification> notification) {
        this.notifications = notification;
        notifyDataSetChanged();
    }

    public void updateNotification(Notification updatedNotification) {
        for (int i = 0; i < notifications.size(); i++) {
            Notification notification = notifications.get(i);
            if (notification.getId() == updatedNotification.getId()) {
                notifications.set(i, updatedNotification);

                notifyItemChanged(i);
                break;
            }
        }
    }

    public class NotificationHolder extends RecyclerView.ViewHolder {
        private TextView notificationText;
        private ImageView notificationImage;
        private Button acceptButton;
        private Button rejectButton;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            notificationImage =  itemView.findViewById(R.id.notification_img);
            notificationText = itemView.findViewById(R.id.TextNotificationName);
            acceptButton = itemView.findViewById(R.id.accept_button_notification);
            rejectButton = itemView.findViewById(R.id.reject_button_notification);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Notification notification);
    }

    public void setAcceptButtonListener(OnItemClickListener acceptButtonListener) {
        this.acceptButtonListener = acceptButtonListener;
    }

    public void setRejectButtonListener(OnItemClickListener rejectButtonListener) {
        this.rejectButtonListener = rejectButtonListener;
    }
}

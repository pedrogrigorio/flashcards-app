package com.example.flashcards_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Friend;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    private List<Friend> friends = new ArrayList<>();

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend_card, parent, false);
        return new FriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Friend currentFriend = friends.get(position);

        holder.name.setText(currentFriend.getName());
        holder.username.setText(currentFriend.getUsername());

        if (!currentFriend.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(currentFriend.getImgSrc())
                    .into(holder.friendImage);
        }

        holder.deleteFriend.setOnClickListener(v -> {
            System.out.println("PRINT: deleteFriend clicked");
        });

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
        notifyDataSetChanged();
    }

    public void insertFriends(Friend friend) {
        friends.add(friend);
        notifyDataSetChanged();
    }

    public class FriendHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView username;
        ImageView friendImage;
        ImageButton deleteFriend;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.friend_name);
            username = itemView.findViewById(R.id.friend_username);
            friendImage = itemView.findViewById(R.id.friend_img);
            deleteFriend = itemView.findViewById(R.id.btn_delete_friend);
        }
    }
}

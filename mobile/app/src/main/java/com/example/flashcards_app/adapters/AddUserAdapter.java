/*
* @Created by: Richard Lira
* @Date: 21/10/2023
* @Function:
* Classe responsável por implementar o código adapter para a tela que adiciona novos amigos.
*
* */

package com.example.flashcards_app.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.models.User;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AddUserAdapter extends RecyclerView.Adapter<AddUserAdapter.AddFriendHolder> {


    private List<User> users = new ArrayList<>();
    private OnItemClickListener addFriendListener;
    private OnItemClickListener deleteFriendListener;

    @NonNull
    @Override
    public AddFriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new AddFriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddFriendHolder holder, int position) {
        User currentUser = users.get(position);
        holder.textViewFriendName.setText(currentUser.getName());
        holder.textViewFriendUserName.setText(currentUser.getUsername());

        if (!currentUser.getImgSrc().isEmpty()) {
            String imageUrl = "http://10.0.2.2:3000/image/" + currentUser.getImgSrc();

            Picasso.get()
                    .load(imageUrl)
                    .into(holder.userImage);
        }

        holder.addFriend.setOnClickListener(v -> {
            addFriendListener.onItemClick(currentUser);
        });

        holder.deleteFriend.setOnClickListener(v -> {
            deleteFriendListener.onItemClick(currentUser);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    static class AddFriendHolder extends RecyclerView.ViewHolder {

        TextView textViewFriendName;
        TextView textViewFriendUserName;
        ImageView userImage;
        Button deleteFriend;
        Button addFriend;

        public AddFriendHolder(@NonNull View itemView) {
            super(itemView);

            textViewFriendName = itemView.findViewById(R.id.TextFriendName);
            textViewFriendUserName = itemView.findViewById(R.id.TextFriendUserName);
            userImage = itemView.findViewById(R.id.friend_img);
            deleteFriend = itemView.findViewById(R.id.btn_delete_friend);
            addFriend = itemView.findViewById(R.id.btn_add_friend);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setAddFriendListener(OnItemClickListener listener) {
        this.addFriendListener = listener;
    }

    public void setDeleteFriendListener(OnItemClickListener listener) {
        this.deleteFriendListener = listener;
    }

}

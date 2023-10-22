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
import android.widget.TextView;
import com.example.flashcards_app.R;
import com.example.flashcards_app.models.User;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AddFriendAdapter extends RecyclerView.Adapter<AddFriendAdapter.AddFriendHolder> {


    private List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public AddFriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_friends, parent, false);
        return new AddFriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddFriendHolder holder, int position) {
        User currentUser = users.get(position);
        holder.textViewFriendName.setText(currentUser.getName());
        holder.textViewFriendUserName.setText(currentUser.getUsername());
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

        private TextView textViewFriendName;
        private TextView textViewFriendUserName;

        public AddFriendHolder(@NonNull View itemView) {
            super(itemView);

            textViewFriendName     = itemView.findViewById(R.id.TextFriendName);
            textViewFriendUserName = itemView.findViewById(R.id.TextFriendUserName);

        }
    }

}

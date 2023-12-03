package com.example.flashcards_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Friend;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.FriendRepository;
import com.example.flashcards_app.util.AppPreferences;
import com.example.flashcards_app.viewmodel.SettingsViewModel;

public class DeleteFriendDialog extends AppCompatDialogFragment {

    int friendId;
    String friendName;

    TextView msgView;

    onDialogResult dialogResult;
    FriendRepository friendRepository;

    public DeleteFriendDialog(String friendName, int friendId) {
        this.friendName = friendName;
        this.friendId = friendId;

        friendRepository = new FriendRepository();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete_friend, null);

        builder.setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("REMOVER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogResult.finish();
                    }
                });

        initViews(view);
        setupInitialConfig();

        return builder.create();
    }

    private void initViews(View view) {
        msgView = view.findViewById(R.id.delete_friend_message);
    }

    private void setupInitialConfig() {
        String msg = "Tem certeza que deseja remover <b>" + friendName + "</b> das amizades?";

        msgView.setText(Html.fromHtml(msg));
    }
    public interface onDialogResult {
        void finish();
    }

    public void setDialogResult(DeleteFriendDialog.onDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}

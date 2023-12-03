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

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Friend;

public class DeleteFriendDialog extends AppCompatDialogFragment {

    String friendName;
    onDialogResult dialogResult;

    public DeleteFriendDialog(String friendName) {
        this.friendName = friendName;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete_friend, null);
        TextView msgView = view.findViewById(R.id.delete_friend_message);
        String msg = "Tem certeza que deseja remover <b>" + friendName + "</b> das amizades?";
        msgView.setText(Html.fromHtml(msg));

        builder.setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("REMOVER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogResult.finish();
                    }
                });

        return builder.create();
    }

    public interface onDialogResult {
        void finish();
    }

    public void setDialogResult(DeleteFriendDialog.onDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}

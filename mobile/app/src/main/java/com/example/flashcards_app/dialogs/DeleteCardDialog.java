package com.example.flashcards_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.Review;

public class DeleteCardDialog extends AppCompatDialogFragment {

    Review currentCard;
    onDialogResult dialogResult;

    public DeleteCardDialog(Review currentCard) {
        this.currentCard = currentCard;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete_deck, null);

        builder.setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("EXCLUIR", new DialogInterface.OnClickListener() {
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

    public void setDialogResult(DeleteCardDialog.onDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}

package com.example.flashcards_app.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards_app.R;

public class FinishedReviewDialog extends AppCompatDialogFragment {

    onDialogFinished dialogFinished;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_finished_review, null);

        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogFinished.finishReview();
                    }
                });

        return super.onCreateDialog(savedInstanceState);

    }



    public interface onDialogFinished {
        void finishReview();
    }

    public void setDialogFinished(FinishedReviewDialog.onDialogFinished dialogFinished) {
        this.dialogFinished = dialogFinished;
    }


}

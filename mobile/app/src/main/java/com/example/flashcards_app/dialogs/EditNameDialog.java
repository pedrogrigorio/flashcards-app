package com.example.flashcards_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;
import com.example.flashcards_app.viewmodel.DeckViewModel;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class EditNameDialog extends AppCompatDialogFragment {

    User currentUser;
    onDialogResult dialogResult;
    EditText updatedName;

    public EditNameDialog(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_name, null);

        updatedName = view.findViewById(R.id.edit_name);
        updatedName.setText(currentUser.getName());

        builder.setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentUser.setName(updatedName.getText().toString());
                        dialogResult.finish(currentUser);
                    }
                });

        return builder.create();
    }

    public interface onDialogResult {
        void finish(User updatedProfile);
    }

    public void setDialogResult(EditNameDialog.onDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}

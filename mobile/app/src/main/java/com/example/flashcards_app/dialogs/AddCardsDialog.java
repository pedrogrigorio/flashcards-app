package com.example.flashcards_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Deck;

public class AddCardsDialog extends AppCompatDialogFragment {

    Deck currentDeck;

    public AddCardsDialog(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_cards, null);

        builder.setView(view);

        TextView cancel = view.findViewById(R.id.cancel_add_cards);
        Button addCard = view.findViewById(R.id.btn_dialog_add_cards);

        cancel.setOnClickListener(v -> {
            dismiss();
        });

        addCard.setOnClickListener(v -> {
            dismiss();
        });

        return builder.create();
    }
}

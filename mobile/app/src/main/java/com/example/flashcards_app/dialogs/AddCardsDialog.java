package com.example.flashcards_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Deck;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddCardsDialog extends AppCompatDialogFragment {

    Deck currentDeck;
    Resources resources;
    TextInputEditText frontCardEditText;
    TextInputEditText backCardEditText;
    onDialogResult dialogResult;

    public AddCardsDialog(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_cards, null);


        Button colorPickerFrontCard = view.findViewById(R.id.btn_change_color_front_card);
        Button colorPickerBackCard = view.findViewById(R.id.btn_change_color_back_card);
        frontCardEditText = view.findViewById(R.id.front_card_text);
        backCardEditText = view.findViewById(R.id.back_card_text);
        resources = getResources();


        builder.setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("ADICIONAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogResult.finish(frontCardEditText.getText().toString(), backCardEditText.getText().toString());
                    }
                });


        colorPickerFrontCard.setOnClickListener(v -> {
            showPopupMenu(colorPickerFrontCard, frontCardEditText);
        });

        colorPickerBackCard.setOnClickListener(v -> {
            showPopupMenu(colorPickerBackCard, backCardEditText);
        });

        return builder.create();
    }

    public interface onDialogResult {
        void finish(String frontText, String backText);
    }

    public void setDialogResult(onDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }



    public void showPopupMenu(View buttonView, EditText cardText) {
        View view = View.inflate(requireContext(), R.layout.dialog_color_picker, null);
        View outDialog = view.findViewById(R.id.out_dialog_color_picker);

        // Fill color arrays
        TypedArray colorResources = resources.obtainTypedArray(R.array.array_color_picker);
        List<Integer> colors = new ArrayList<>();

        List<ImageView> colorsViewsList = new ArrayList<>();

        for (int i = 0; i < colorResources.length(); i++) {
            int color = colorResources.getColor(i, Color.BLACK);
            colors.add(color);
        }

        colorResources.recycle();

        for (int i = 1; i <= 8; i++) {
            int colorId = getResources().getIdentifier("color_" + i, "id", requireContext().getPackageName());
            ImageView color = view.findViewById(colorId);
            colorsViewsList.add(color);
        }

        // Popup configuration
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.MATCH_PARENT;

        PopupWindow popupWindow = new PopupWindow(view, width, height, false);

        popupWindow.showAtLocation(buttonView, Gravity.CENTER, 0, 0);

        // Color picker configuration
        for (int i = 0; i < colorsViewsList.size(); i++) {
            View colorView = colorsViewsList.get(i);
            int color = colors.get(i);

            colorView.setOnClickListener(v -> {
                Spannable spannableString = new SpannableStringBuilder(cardText.getText());

                spannableString.setSpan(new ForegroundColorSpan(color),
                        cardText.getSelectionStart(),
                        cardText.getSelectionEnd(),
                        0);
                cardText.setText(spannableString);

                popupWindow.dismiss();
            });
        }

        outDialog.setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }
}

package com.example.flashcards_app.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Deck;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditDeckDialog extends AppCompatDialogFragment {

    Deck currentDeck;
    ImageView close;
    ImageView currentDeckImg;
    FloatingActionButton camButton;
    TextView cancel;
    Button edit;
    EditText title;

    public EditDeckDialog(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_deck, null);

        builder.setView(view);

        close = view.findViewById(R.id.edit_deck_close_popup);
        cancel = view.findViewById(R.id.cancel_edit_deck);
        edit = view.findViewById(R.id.btn_dialog_edit_deck);
        camButton = view.findViewById(R.id.btn_cam_edit_deck);
        currentDeckImg = view.findViewById(R.id.deck_img_edit_deck);

        Drawable drawable = currentDeck.getDeckImage().getDrawable();
        currentDeckImg.setImageDrawable(drawable);

        title = view.findViewById(R.id.deck_title_edit_deck);
        title.setText(currentDeck.getTitle());

        close.setOnClickListener(v -> {
            dismiss();
        });

        cancel.setOnClickListener(v -> {
            dismiss();
        });

        edit.setOnClickListener(v -> {
            ImageView deckImg = currentDeck.getDeckImage();
            deckImg.setImageDrawable(currentDeckImg.getDrawable());

            currentDeck.setTitle(title.getText().toString());
            dismiss();
        });

        camButton.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop(1f, 1f)
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .createIntent(intent -> {
                        startForMediaPickerResult.launch(intent);
                        return null;
                    });
        });

        return builder.create();
    }

    private final ActivityResultLauncher<Intent> startForMediaPickerResult = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            Intent data = result.getData();
            if (data != null && result.getResultCode() == Activity.RESULT_OK) {
                Uri newImgUri = data.getData();
                currentDeckImg.setImageURI(newImgUri);
            } else {
                Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            }
        });
}

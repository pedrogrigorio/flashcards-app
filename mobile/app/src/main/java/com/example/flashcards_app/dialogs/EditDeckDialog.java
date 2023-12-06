package com.example.flashcards_app.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.example.flashcards_app.viewmodel.DeckViewModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EditDeckDialog extends AppCompatDialogFragment {

    Deck currentDeck;
    Deck updatedDeck;
    onDialogResult dialogResult;

    ImageView close;
    ImageView currentDeckImg;
    FloatingActionButton camButton;
    EditText title;
    Uri newImgUri;

    public EditDeckDialog(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_deck, null);

        builder.setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updatedDeck.setId(currentDeck.getId());
                        updatedDeck.setTitle(title.getText().toString());
                        updatedDeck.setLearnCardsNumber(currentDeck.getLearnCardsNumber());
                        updatedDeck.setNewCardsNumber(currentDeck.getNewCardsNumber());
                        updatedDeck.setReviewCardsNumber(currentDeck.getReviewCardsNumber());
                        dialogResult.finish(updatedDeck, newImgUri);
                    }
                });

        updatedDeck = new Deck();
        close = view.findViewById(R.id.edit_deck_close_popup);
        camButton = view.findViewById(R.id.btn_cam_edit_deck);
        currentDeckImg = view.findViewById(R.id.deck_img_edit_deck);
        title = view.findViewById(R.id.deck_title_edit_deck);
        String imageUrl = "http://10.0.2.2:3000/image/" + currentDeck.getImgSrc();
        if (currentDeck != null && !currentDeck.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .into(currentDeckImg);
        }

        title.setText(currentDeck.getTitle());

        close.setOnClickListener(v -> {
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
                newImgUri = data.getData();
                currentDeckImg.setImageURI(newImgUri);
                // TODO: upload image on backend

                // url_backend must be loaded by url given by backend
                String url_backend = newImgUri.toString();
                updatedDeck.setImgSrc(url_backend);
            } else {
                Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            }
        });

    public interface onDialogResult {
        void finish(Deck updatedDeck, Uri newImgUri);
    }

    public void setDialogResult(onDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }


}

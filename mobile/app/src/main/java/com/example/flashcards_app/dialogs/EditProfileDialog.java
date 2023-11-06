package com.example.flashcards_app.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.models.User;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class EditProfileDialog extends AppCompatDialogFragment {

    User user;
    User updatedUser;
    onDialogResult dialogResult;

    ImageView profileImg;
    FloatingActionButton camButton;
    EditText name;

    public EditProfileDialog(User user) {
        this.user = user;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_profile, null);

        builder.setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updatedUser.setName(name.getText().toString());
                        dialogResult.finish(updatedUser);
                    }
                });

        camButton = view.findViewById(R.id.btn_edit_img);
        profileImg = view.findViewById(R.id.profile_img);
        name = view.findViewById(R.id.edit_name);

        if (!user.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(user.getImgSrc())
                    .into(profileImg);
        }

        name.setText(user.getName());

        updatedUser = new User(user.getName(), user.getUsername());

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
                profileImg.setImageURI(newImgUri);
                // TODO: upload image on backend

                // url_backend must be loaded by url given by backend
                String url_backend = newImgUri.toString();
                updatedUser.setImgSrc(url_backend);
            } else {
                Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            }
        });

    public interface onDialogResult {
        void finish(User updatedUser);
    }

    public void setDialogResult(onDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }


}

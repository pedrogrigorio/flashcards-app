package com.example.flashcards_app.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class EditProfileDialog extends AppCompatDialogFragment {

    User user;
    User updatedUser;
    onDialogResult dialogResult;

    ImageView profileImg;
    FloatingActionButton camButton;
    TextInputLayout nameLayout;
    TextInputEditText nameEditText;

    Button positiveButton;

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
                        updatedUser.setName(nameEditText.getText().toString());
                        dialogResult.finish(updatedUser);
                    }
                });

        AlertDialog dialog = builder.create();
//        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        button.setBackgroundColor(Color.BLUE);
//        button.setTextColor(Color.GREEN);

        camButton = view.findViewById(R.id.btn_edit_img);
        profileImg = view.findViewById(R.id.profile_img);
        nameLayout = view.findViewById(R.id.edit_name_field);
        nameEditText = view.findViewById(R.id.edit_name_editText);

        if (!user.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(user.getImgSrc())
                    .into(profileImg);
        }

        nameEditText.setText(user.getName());

        updatedUser = new User(user.getName(), user.getUsername());

        configNameFieldValidation();

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

        return dialog;
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

    private void configNameFieldValidation() {
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString();
                boolean isValid;

                if (name.length() >= 2) {
                    isValid = name.matches("^[a-zA-Z][a-zA-Z\\s]*[a-zA-Z]$");
                } else {
                    nameLayout.setHelperText("Nome deve ter pelo menos 2 letras e pode conter apenas letras e espaços.");
                    isValid = false;
                }

                //editButton.setEnabled(isValid);

                boolean startsWithLetters = name.matches("^[a-zA-Z].*");
                boolean containsOnlyLettersAndSpaces = name.matches("^[a-zA-Z\\s]*$");
                boolean endsWithLetters = name.matches(".*[a-zA-Z]$");

                if (!isValid) {
                    if (!startsWithLetters) {
                        nameLayout.setError("Nome deve iniciar com letras.");
                    } else if (!containsOnlyLettersAndSpaces) {
                        nameLayout.setError("Nome pode conter apenas letras e espaços.");
                    } else if (!endsWithLetters) {
                        nameLayout.setError("Nome não pode terminar com espaços em branco.");
                    }
                } else {
                    nameLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


}

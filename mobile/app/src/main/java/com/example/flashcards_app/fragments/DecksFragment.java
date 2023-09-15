package com.example.flashcards_app.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DecksFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private LinearLayout linearLayout;
    private ConstraintLayout mainActivityRootLayout;
    private int cardCount = 0;
    private List<Deck> deckList = new ArrayList<>();

    private Deck currentDeckOptionsPopup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decks, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        linearLayout = view.findViewById(R.id.decksLinearLayout);
        mainActivityRootLayout = mainActivity.getRootLayout();

        Button addButton = mainActivity.getCreateDeckButton();

        addButton.setOnClickListener(v -> {
            addNewCard();
        });

        return view;
    }

    private void addNewCard() {
        /* Inflar layout */
        View cardView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_card_layout, null);

        // Layout config
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );

        Resources r = getContext().getResources();
        int margin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                24,
                r.getDisplayMetrics()
        );

        layoutParams.setMargins(margin, 0, margin, margin);
        cardView.setLayoutParams(layoutParams);

        /* Create object */
        Deck deck = new Deck(cardCount);

        // TextViews References
        TextView deckTitle = cardView.findViewById(R.id.deckTitle);
        int uniqueTitleId = View.generateViewId();
        deckTitle.setId(uniqueTitleId);

        TextView newCardsNumber = cardView.findViewById(R.id.newCardsNumber);
        int newCardsNumberUniqueId = View.generateViewId();
        newCardsNumber.setId(newCardsNumberUniqueId);

        TextView learnCardsNumber = cardView.findViewById(R.id.learnCardsNumber);
        int learnCardsNumberUniqueId = View.generateViewId();
        learnCardsNumber.setId(learnCardsNumberUniqueId);

        TextView reviewCardsNumber = cardView.findViewById(R.id.reviewCardsNumber);
        int reviewCardsNumberUniqueId = View.generateViewId();
        reviewCardsNumber.setId(reviewCardsNumberUniqueId);

        deck.setTitleTextView(deckTitle);
        deck.setNewCardsNumberTextView(newCardsNumber);
        deck.setLearnCardsNumberTextView(learnCardsNumber);
        deck.setReviewCardsNumberTextView(reviewCardsNumber);

        // Buttons reference and onClickListener
        AppCompatButton editButton = cardView.findViewById(R.id.btn_edit_deck);
        int uniqueEditButtonId = View.generateViewId();
        editButton.setId(uniqueEditButtonId);

        editButton.setOnClickListener(v -> {
            showPopupMenu(requireContext(), editButton, deck);
        });

        AppCompatButton reviewButton = cardView.findViewById(R.id.btn_review);
        int uniqueReviewButtonId = View.generateViewId();
        reviewButton.setId(uniqueReviewButtonId);

        reviewButton.setOnClickListener(v -> {
            // Encaminhar para tela de revisões passando o deck (ou id)
            Toast.makeText(requireContext(), "review deck: " + deck.getId(), Toast.LENGTH_SHORT).show();
        });

        // Persist
        linearLayout.addView(cardView);
        deckList.add(deck);
        cardCount++;
    }

    public void showPopupMenu(Context context, View v, Deck deck) {
        currentDeckOptionsPopup = deck;
        PopupMenu popup = new PopupMenu(context, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.deck_options_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        // currentDeckOptionsPopup <-- current deck object
        if (id == R.id.item1) {
            showAddCardsPopupWindow();
            Toast.makeText(requireContext(), "Add cards in deck " + currentDeckOptionsPopup.getId(), Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.item2) {
            showEditDeckPopupWindow();
            return true;
        } else if (id == R.id.item3) {
            showDeleteDeckPopupWindow();
            Toast.makeText(requireContext(), "Delete deck " + currentDeckOptionsPopup.getId(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void showEditDeckPopupWindow() {
        View view = View.inflate(requireContext(), R.layout.dialog_edit_deck, null);
        View outDialog = view.findViewById(R.id.out_dialog_edit_deck);
        ImageView close = view.findViewById(R.id.edit_deck_close_popup);
        TextView cancel = view.findViewById(R.id.cancel_edit_deck);
        Button edit = view.findViewById(R.id.btn_dialog_edit_deck);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        PopupWindow popupWindow = new PopupWindow(view, width, height, false);

        popupWindow.showAtLocation(mainActivityRootLayout, Gravity.CENTER, 0, 0);

        outDialog.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        close.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        cancel.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        edit.setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }

    public void showDeleteDeckPopupWindow() {
        View view = View.inflate(requireContext(), R.layout.dialog_delete_deck, null);
        View outDialog = view.findViewById(R.id.out_dialog_delete_deck);
        TextView cancel = view.findViewById(R.id.cancel_delete_deck);
        Button exclude = view.findViewById(R.id.btn_dialog_delete_deck);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        PopupWindow popupWindow = new PopupWindow(view, width, height, false);

        popupWindow.showAtLocation(mainActivityRootLayout, Gravity.CENTER, 0, 0);

        exclude.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        cancel.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        outDialog.setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }

    public void showAddCardsPopupWindow() {
        View view = View.inflate(requireContext(), R.layout.dialog_add_cards, null);
        View outDialog = view.findViewById(R.id.out_dialog_add_cards);
        TextView cancel = view.findViewById(R.id.cancel_add_cards);
        Button addCard = view.findViewById(R.id.btn_dialog_add_cards);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        PopupWindow popupWindow = new PopupWindow(view, width, height, false);

        popupWindow.showAtLocation(mainActivityRootLayout, Gravity.CENTER, 0, 0);

        addCard.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        cancel.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        outDialog.setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }
}
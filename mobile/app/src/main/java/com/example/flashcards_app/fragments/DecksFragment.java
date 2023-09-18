package com.example.flashcards_app.fragments;

import android.content.Context;
import android.content.Intent;
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

import com.example.flashcards_app.activities.NotificationActivity;
import com.example.flashcards_app.activities.ProfileActivity;
import com.example.flashcards_app.activities.ReviewActivity;
import com.example.flashcards_app.dialogs.AddCardsDialog;
import com.example.flashcards_app.dialogs.DeleteDeckDialog;
import com.example.flashcards_app.dialogs.EditDeckDialog;
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

    private Deck currentDeck;

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
        View cardView = LayoutInflater.from(requireContext()).inflate(R.layout.item_card_layout, null);

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

        ImageView deckImg = cardView.findViewById(R.id.deck_img);
        int imgUniqueId = View.generateViewId();
        deckImg.setId(imgUniqueId);

        deck.setTitleTextView(deckTitle);
        deck.setNewCardsNumberTextView(newCardsNumber);
        deck.setLearnCardsNumberTextView(learnCardsNumber);
        deck.setReviewCardsNumberTextView(reviewCardsNumber);
        deck.setDeckImage(deckImg);

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
            Toast.makeText(requireContext(), "review deck: " + deck.getId(), Toast.LENGTH_SHORT).show();
            Intent in = new Intent(getActivity(), ReviewActivity.class);
            startActivity(in);
        });

        // Persist
        linearLayout.addView(cardView);
        deckList.add(deck);
        cardCount++;
    }

    public void showPopupMenu(Context context, View v, Deck deck) {
        currentDeck = deck;
        PopupMenu popup = new PopupMenu(context, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.deck_options_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {
            AddCardsDialog dialog = new AddCardsDialog(currentDeck);
            dialog.show(getActivity().getSupportFragmentManager(), "edit_deck_popup");
            return true;
        } else if (id == R.id.item2) {
            EditDeckDialog dialog = new EditDeckDialog(currentDeck);
            dialog.show(getActivity().getSupportFragmentManager(), "edit_deck_popup");
            return true;
        } else if (id == R.id.item3) {
            DeleteDeckDialog dialog = new DeleteDeckDialog(currentDeck);
            dialog.show(getActivity().getSupportFragmentManager(), "delete_deck_popup");
            return true;
        }

        return false;
    }
}
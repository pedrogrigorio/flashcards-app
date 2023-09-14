package com.example.flashcards_app;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DecksFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private LinearLayout linearLayout;
    private int cardCount = 0;
    private List<Deck> deckList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decks, container, false);

        linearLayout = view.findViewById(R.id.decksLinearLayout);
        Button addButton = ((MainActivity) requireActivity()).getCreateDeckButton();

        addButton.setOnClickListener(v -> {
            addNewCard();
        });

        return view;
    }

    private void addNewCard() {
        /* Inflar layout */
        View cardView = LayoutInflater.from(requireContext()).inflate(R.layout.card_layout, null);

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

        // Edit button onClick
        AppCompatButton editButton = cardView.findViewById(R.id.btn_edit_deck);
        int uniqueEditButtonId = View.generateViewId();
        editButton.setId(uniqueEditButtonId);

        editButton.setOnClickListener(v -> {
//            edit(deck);
            showPopupMenu(requireContext(), editButton);
        });

        // Persist
        linearLayout.addView(cardView);
        deckList.add(deck);
        cardCount++;
    }

    public void showPopupMenu(Context context, View v) {
        PopupMenu popup = new PopupMenu(context, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.deck_options_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {
            Toast.makeText(requireContext(), "item 1", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.item2) {
            Toast.makeText(requireContext(), "item 2", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.item3) {
            Toast.makeText(requireContext(), "item 3", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    public void edit(Deck deck) {
        System.out.println("editando " + deck.getTitle());
        deck.setTitle("_" + deck.getTitle());
    }


}
package com.example.flashcards_app;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DecksFragment extends Fragment {

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
            edit(deck);
        });

        // Persist
        linearLayout.addView(cardView);
        deckList.add(deck);
        cardCount++;
    }

    public void edit(Deck deck) {
        System.out.println("editando " + deck.getTitle());
        deck.setTitle("_" + deck.getTitle());
    }
}
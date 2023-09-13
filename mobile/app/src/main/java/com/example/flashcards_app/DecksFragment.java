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

    private FrameLayout frameLayout;
    private LinearLayout linearLayout;
    private int cardCount = 0;

    private List<Deck> deckList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decks, container, false);

        frameLayout = view.findViewById(R.id.decksLayout);
        linearLayout = view.findViewById(R.id.decksLinearLayout);
        Button addButton = ((MainActivity) requireActivity()).getCreateDeckButton();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCard();
            }
        });

        return view;
    }

    private void addNewCard() {
        // Inflar layout
        View cardView = LayoutInflater.from(requireContext()).inflate(R.layout.card_layout, null);

        // Layout config
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );

        Resources r = getContext().getResources();
        int x_margin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                24,
                r.getDisplayMetrics()
        );

        layoutParams.setMargins(x_margin, 0, x_margin, x_margin);
        cardView.setLayoutParams(layoutParams);

        // Deck Title
        TextView deckTitle = cardView.findViewById(R.id.deckTitle);
        int uniqueTitleId = View.generateViewId();
        deckTitle.setId(uniqueTitleId);

        // Objeto
        Deck deck = new Deck(cardCount, "Baralho" + cardCount, deckTitle);
        deck.setTitle(deck.getTitle());

        // Edit button onClick
        AppCompatButton editButton = cardView.findViewById(R.id.btn_edit_deck);
        int uniqueEditButtonId = View.generateViewId();
        editButton.setId(uniqueEditButtonId);

        editButton.setOnClickListener(v -> {
            edit(deck);
        });

        // Add to screen and list
        linearLayout.addView(cardView);
        deckList.add(deck);
        cardCount++;
    }

    public void edit(Deck deck) {
        System.out.println("editando " + deck.getTitle());
        deck.setTitle("_" + deck.getTitle());
    }
}
package com.example.flashcards_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.fragments.DecksFragment;
import com.example.flashcards_app.models.Deck;

import java.util.ArrayList;
import java.util.List;
import com.example.flashcards_app.R;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.DeckHolder> {

    private List<Deck> decks = new ArrayList<>();
    private DecksFragment parentFragment;

    public DeckAdapter(DecksFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @NonNull
    @Override
    public DeckHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_deck_card, parent, false);
        return new DeckHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckHolder holder, int position) {
        Deck currentDeck = decks.get(position);
        holder.titleTextView.setText(currentDeck.getTitle());

        holder.editButton.setOnClickListener(v -> {
            parentFragment.showPopupMenu(parentFragment.getContext(), holder.editButton, currentDeck);
        });
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
        notifyDataSetChanged();
    }

    class DeckHolder extends RecyclerView.ViewHolder {
        private ImageView deckImage;

        private TextView titleTextView;
        private TextView newCardsNumberTextView;
        private TextView learnCardsNumberTextView;
        private TextView reviewCardsNumberTextView;

        private Button editButton;

        public DeckHolder(View itemView) {
            super(itemView);
            deckImage = itemView.findViewById(R.id.deck_img);
            titleTextView = itemView.findViewById(R.id.deck_title);
            newCardsNumberTextView = itemView.findViewById(R.id.newCardsNumber);
            learnCardsNumberTextView = itemView.findViewById(R.id.learnCardsNumber);
            reviewCardsNumberTextView = itemView.findViewById(R.id.reviewCardsNumber);
            editButton = itemView.findViewById(R.id.btn_edit_deck);
        }
    }
}

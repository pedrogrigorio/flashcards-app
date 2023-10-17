package com.example.flashcards_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards_app.activities.ReviewActivity;
import com.example.flashcards_app.dialogs.AddCardsDialog;
import com.example.flashcards_app.dialogs.DeleteDeckDialog;
import com.example.flashcards_app.dialogs.EditDeckDialog;
import com.example.flashcards_app.models.Deck;

import java.util.ArrayList;
import java.util.List;
import com.example.flashcards_app.R;
import com.squareup.picasso.Picasso;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.DeckHolder> {

    private List<Deck> decks = new ArrayList<>();
    private OnItemClickListener optionListener;
    private onReviewButtonListener reviewButtonListener;

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
        holder.newCardsNumberTextView.setText(currentDeck.getNewCardsNumber() + "");
        holder.reviewCardsNumberTextView.setText(currentDeck.getReviewCardsNumber() + "");
        holder.learnCardsNumberTextView.setText(currentDeck.getLearnCardsNumber() + "");

        if (!currentDeck.getImgSrc().isEmpty()) {
            Picasso.get()
                    .load(currentDeck.getImgSrc())
                    .into(holder.deckImage);
        }

        holder.editButton.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), holder.editButton);
            popup.inflate(R.menu.deck_options_menu);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.item1) {
                        optionListener.onItemClick(currentDeck, 0, position);
                        return true;
                    } else if (id == R.id.item2) {
                        optionListener.onItemClick(currentDeck, 1, position);
                        return true;
                    } else if (id == R.id.item3) {
                        optionListener.onItemClick(currentDeck, 2, position);
                        return true;
                    }

                    return false;
                }
            });

            popup.show();
        });

        holder.reviewButton.setOnClickListener(v -> {
            reviewButtonListener.onItemClick(currentDeck);
        });
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
        notifyDataSetChanged();
    }

    public class DeckHolder extends RecyclerView.ViewHolder {
        ImageView deckImage;

        TextView titleTextView;
        TextView newCardsNumberTextView;
        TextView learnCardsNumberTextView;
        TextView reviewCardsNumberTextView;

        Button editButton;
        Button reviewButton;

        public DeckHolder(@NonNull View itemView) {
            super(itemView);
            deckImage = itemView.findViewById(R.id.deck_img);

            titleTextView = itemView.findViewById(R.id.deck_title);
            newCardsNumberTextView = itemView.findViewById(R.id.new_cards_number);
            learnCardsNumberTextView = itemView.findViewById(R.id.learn_cards_number);
            reviewCardsNumberTextView = itemView.findViewById(R.id.review_cards_number);

            editButton = itemView.findViewById(R.id.btn_edit_deck);
            reviewButton = itemView.findViewById(R.id.btn_review);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Deck deck, int option, int position);
    }

    public void setOptionListener(OnItemClickListener optionListener) {
        this.optionListener = optionListener;
    }

    public interface onReviewButtonListener {
        void onItemClick(Deck deck);
    }

    public void setReviewButtonListener(onReviewButtonListener reviewButtonListener) {
        this.reviewButtonListener = reviewButtonListener;
    }

}

package com.example.flashcards_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards_app.activities.ReviewActivity;
import com.example.flashcards_app.adapters.DeckAdapter;
import com.example.flashcards_app.dialogs.AddCardsDialog;
import com.example.flashcards_app.dialogs.DeleteDeckDialog;
import com.example.flashcards_app.dialogs.EditDeckDialog;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.MainActivity;
import com.example.flashcards_app.viewmodel.DeckViewModel;

import java.util.ArrayList;
import java.util.List;

public class DecksFragment extends Fragment {

    public static final int ADD_CARDS_REQUEST = 1;
    public static final int EDIT_DECK_REQUEST = 2;
    public static final int DELETE_DECK_REQUEST = 3;

    private DeckViewModel deckViewModel;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decks, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        Button addButton = mainActivity.getCreateDeckButton();
        context = getActivity();

        DeckAdapter adapter = new DeckAdapter(getActivity());
        adapter.setListener(new DeckAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Deck deck, int option, int position) {
                FragmentManager manager = getChildFragmentManager();

                if (option == 0) {
                    AddCardsDialog dialog = new AddCardsDialog(deck);
                    dialog.show(manager, "edit_deck_popup");
                } else if (option == 1) {
                    EditDeckDialog dialog = new EditDeckDialog(deck);
                    dialog.setDialogResult(new EditDeckDialog.onDialogResult() {
                        @Override
                        public void finish(Deck updatedDeck) {
                            deckViewModel.updateDeck(updatedDeck, position);
                        }
                    });
                    dialog.show(manager, "edit_deck_popup");
                } else if (option == 2) {
                    DeleteDeckDialog dialog = new DeleteDeckDialog(deck);
                    dialog.setDialogResult(new DeleteDeckDialog.onDialogResult() {
                        @Override
                        public void finish() {
                            deckViewModel.deleteDeck(position);
                        }
                    });
                    dialog.show(manager, "delete_deck_popup");
                } else {
                    System.out.println("Error");
                }
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.decks_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        deckViewModel.getDecks().observe(getActivity(), new Observer<List<Deck>>() {
            @Override
            public void onChanged(List<Deck> decks) {
                adapter.setDecks(decks);
            }
        });

        addButton.setOnClickListener(v -> {
            deckViewModel.insertDeck(new Deck());
        });

        return view;
    }



}
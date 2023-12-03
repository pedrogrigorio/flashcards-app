package com.example.flashcards_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.flashcards_app.activities.ReviewActivity;
import com.example.flashcards_app.adapters.AddUserAdapter;
import com.example.flashcards_app.adapters.DeckAdapter;
import com.example.flashcards_app.dialogs.AddCardsDialog;
import com.example.flashcards_app.dialogs.DeleteDeckDialog;
import com.example.flashcards_app.dialogs.EditDeckDialog;
import com.example.flashcards_app.models.Deck;
import com.example.flashcards_app.R;
import com.example.flashcards_app.activities.HomeActivity;
import com.example.flashcards_app.viewmodel.DeckViewModel;

import java.util.List;

public class DecksFragment extends Fragment {

    private DeckViewModel deckViewModel;
    private RecyclerView recyclerView;
    private DeckAdapter adapter;

    Button addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decks, container, false);

        HomeActivity homeActivity = (HomeActivity) getActivity();

        initViews(view, homeActivity);

        setupInitialConfig();

        configAdapter();

        configRecyclerView();

        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        fetchAllDecks();



        return view;
    }

    private void initViews(View view, HomeActivity homeActivity) {
        recyclerView = view.findViewById(R.id.decks_recycler_view);
        addButton = homeActivity.getCreateDeckButton();
    }

    private void setupInitialConfig() {
        adapter = new DeckAdapter();

        addButton.setOnClickListener(v -> {
            deckViewModel.insertDeck(new Deck());
        });

    }

    private void configAdapter() {
        adapter.setOptionListener(new DeckAdapter.OnItemClickListener() {
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
                            deckViewModel.deleteDeck(deck, position);
                        }
                    });
                    dialog.show(manager, "delete_deck_popup");
                } else {
                    System.out.println("Error");
                }
            }
        });

        adapter.setReviewButtonListener(new DeckAdapter.onReviewButtonListener() {
            @Override
            public void onItemClick(Deck deck) {
                // TODO: uncomment this code later
//                if (deck.getLearnCardsNumber() == 0 && deck.getNewCardsNumber() == 0 && deck.getReviewCardsNumber() == 0) {
//                    Toast.makeText(context, "Todos os cards já foram revisados", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                Intent in = new Intent(getContext(), ReviewActivity.class);
                in.putExtra("deckId", deck.getId());
                getContext().startActivity(in);
            }
        });
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void fetchAllDecks() {
        deckViewModel.getDecks().observe(getActivity(), new Observer<List<Deck>>() {
            @Override
            public void onChanged(List<Deck> decks) {
                adapter.setDecks(decks);
            }
        });
    }
}
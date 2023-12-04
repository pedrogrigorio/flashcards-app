package com.example.flashcards_app.dto;

public class DeleteCardDTO {
    // RESOLVER DEBITO TECNICO APOS ISSO

    private int deckId;
    private int cardId;

    public DeleteCardDTO(int deckId, int cardId) {
        this.deckId = deckId;
        this.cardId = cardId;
    }
}

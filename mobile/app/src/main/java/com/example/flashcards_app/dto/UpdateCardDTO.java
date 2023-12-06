package com.example.flashcards_app.dto;

public class UpdateCardDTO {

    private int deckId;
    private int cardId;
    private String frontText;
    private String backText;

    public UpdateCardDTO(int deckId, int cardId, String frontText, String backText) {
        this.deckId = deckId;
        this.cardId = cardId;
        this.frontText = frontText;
        this.backText = backText;
    }
}

package com.example.flashcards_app.dto;

public class CreateCardDTO {

    private int deckId;
    private String frontText;
    private String backText;


    public CreateCardDTO(int deckId, String frontText, String backText) {
        this.deckId = deckId;
        this.frontText = frontText;
        this.backText = backText;
    }

}

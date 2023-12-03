package com.example.flashcards_app.dto;

public class UpdateDeckDTO {

    private int deckId;
    private String title;
    private String imgSrc;
    private  int newCardsCount;
    private int learnCardsCount;
    private int reviewCardsCount;


    public UpdateDeckDTO(int deckId,
                         String title,
                         String imgSrc,
                         int newCardsCount,
                         int learnCardsCount,
                         int reviewCardsCount)
    {

        this.deckId = deckId;
        this.title = title;
        this.newCardsCount = newCardsCount;
        this.learnCardsCount = learnCardsCount;
        this.reviewCardsCount = reviewCardsCount;
    }
}

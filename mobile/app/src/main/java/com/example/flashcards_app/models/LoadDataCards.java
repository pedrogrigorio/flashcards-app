package com.example.flashcards_app.models;

import java.util.Vector;

public class LoadDataCards {

    Vector<CardScheme> cardSchemes;
    private int amount;
    private int currentBoundary;
    public LoadDataCards() {
        cardSchemes = new Vector<>();
    }

    public void LoadCards() {
        cardSchemes.add(new CardScheme(1,"Hello", "Olá"));
        cardSchemes.add(new CardScheme(2,"Goodbye", "Adeus"));
        cardSchemes.add(new CardScheme(3,"Thank you", "Obrigado"));
        cardSchemes.add(new CardScheme(4,"I love you", "Eu amo você"));
        this.setAmount(cardSchemes.size());

    }
    public String[] getDataCard(int request) {
        if (request < 1) return null;
        else if (request > this.amount) return  null;
        else if (request > this.currentBoundary) {
            currentBoundary+=1;
            return new String[]{cardSchemes.get(currentBoundary).getFront(), cardSchemes.get(currentBoundary).getBack()};
        }
        else {
            return new String[]{cardSchemes.get(request).getFront(), cardSchemes.get(request).getBack()};
        }

    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() { return this.amount; }
    public int getCurrentBoundary() {
        return this.currentBoundary;
    }



}

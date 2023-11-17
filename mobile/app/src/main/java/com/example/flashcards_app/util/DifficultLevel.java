package com.example.flashcards_app.util;

public enum DifficultLevel {
    EASY(0),
    GOOD(1),
    HARD(2);

    private final int value;

    DifficultLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

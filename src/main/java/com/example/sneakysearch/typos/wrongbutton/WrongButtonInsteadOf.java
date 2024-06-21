package com.example.sneakysearch.typos.wrongbutton;

public final class WrongButtonInsteadOf implements WrongButton {
    final String word;
    final int position;
    final String letter;

    public WrongButtonInsteadOf(String word, int position, String letter) {
        this.word = word;
        this.position = position;
        this.letter = letter;
    }

    @Override
    public String value() {
        return new StringBuilder(word).replace(position, position + 1, letter).toString();
    }
}

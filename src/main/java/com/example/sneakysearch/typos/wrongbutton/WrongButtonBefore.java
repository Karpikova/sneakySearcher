package com.example.sneakysearch.typos.wrongbutton;

public final class WrongButtonBefore implements WrongButton{
    final String word;
    final int position;
    final String letter;

    public WrongButtonBefore(String word, int position, String letter) {
        this.word = word;
        this.position = position;
        this.letter = letter;
    }
    @Override
    public String value() {
        return new StringBuilder(word).insert(position, letter).toString();
    }
}

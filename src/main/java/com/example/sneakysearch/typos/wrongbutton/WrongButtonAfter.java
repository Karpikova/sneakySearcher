package com.example.sneakysearch.typos.wrongbutton;

import com.example.sneakysearch.typos.WordWithTypo;

public final class WrongButtonAfter implements WordWithTypo {
    final String phrase;
    final int position;
    final String letter;

    public WrongButtonAfter(String phrase, int position, String letter) {
        this.phrase = phrase;
        this.position = position;
        this.letter = letter;
    }

    @Override
    public String value() {
        return new StringBuilder(phrase).insert(position + 1, letter).toString();
    }
}

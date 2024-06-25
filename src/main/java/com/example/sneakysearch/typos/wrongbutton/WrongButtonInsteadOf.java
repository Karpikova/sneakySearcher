package com.example.sneakysearch.typos.wrongbutton;

import com.example.sneakysearch.typos.WordWithTypo;

public final class WrongButtonInsteadOf implements WordWithTypo {
    final String phrase;
    final int position;
    final String letter;

    public WrongButtonInsteadOf(String phrase, int position, String letter) {
        this.phrase = phrase;
        this.position = position;
        this.letter = letter;
    }

    @Override
    public String value() {
        return new StringBuilder(phrase).replace(position, position + 1, letter).toString();
    }
}

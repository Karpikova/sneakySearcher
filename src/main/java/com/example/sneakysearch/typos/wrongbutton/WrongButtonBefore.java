package com.example.sneakysearch.typos.wrongbutton;

import com.example.sneakysearch.typos.WordWithTypo;

public final class WrongButtonBefore implements WordWithTypo {
    private final String phrase;
    private final int position;
    private final String letter;

    public WrongButtonBefore(String phrase, int position, String letter) {
        this.phrase = phrase;
        this.position = position;
        this.letter = letter;
    }

    @Override
    public String value() {
        return new StringBuilder(phrase).insert(position, letter).toString();
    }
}

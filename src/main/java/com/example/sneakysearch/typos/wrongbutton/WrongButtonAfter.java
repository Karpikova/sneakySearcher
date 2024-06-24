package com.example.sneakysearch.typos.wrongbutton;

import com.example.sneakysearch.typos.WordWithTypo;

public final class WrongButtonAfter implements WordWithTypo {
    final String word;
    final int position;
    final String letter;

    public WrongButtonAfter(String word, int position, String letter) {
        this.word = word;
        this.position = position;
        this.letter = letter;
    }

    @Override
    public String value() {
        return new StringBuilder(word).insert(position + 1, letter).toString();
    }
}

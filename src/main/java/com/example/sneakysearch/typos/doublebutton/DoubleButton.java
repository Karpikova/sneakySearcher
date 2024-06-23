package com.example.sneakysearch.typos.doublebutton;

import com.example.sneakysearch.typos.Typo;

public final class DoubleButton implements Typo {
    final String word;
    final int position;

    public DoubleButton(String word, int position) {
        this.word = word;
        this.position = position;
    }

    @Override
    public String value() {
        final String letter = word.substring(position, position + 1);
        return new StringBuilder(word).insert(position, letter).toString();
    }
}

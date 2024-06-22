package com.example.sneakysearch.typos.typo;

public final class MissedInnerButton implements Typo{
    final String word;
    final int position;

    public MissedInnerButton(String word, int position) {
        this.word = word;
        this.position = position;
    }

    @Override
    public String value() {
        return word.substring(0, position) + word.substring(position + 1);
    }
}

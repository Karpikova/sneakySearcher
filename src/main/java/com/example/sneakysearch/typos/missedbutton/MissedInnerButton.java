package com.example.sneakysearch.typos.missedbutton;

import com.example.sneakysearch.typos.WordWithTypo;

public final class MissedInnerButton implements WordWithTypo {
    private final String phrase;
    private final int position;

    public MissedInnerButton(String phrase, int position) {
        this.phrase = phrase;
        this.position = position;
    }

    @Override
    public String value() {
        return phrase.substring(0, position) + phrase.substring(position + 1);
    }
}

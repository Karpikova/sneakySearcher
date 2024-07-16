package com.example.sneakysearch.typos.mixedbuttons;

import com.example.sneakysearch.typos.WordWithTypo;

public final class MixedButtons implements WordWithTypo {
    private final String phrase;
    private final int position;

    public MixedButtons(String phrase, int position) {
        this.phrase = phrase;
        this.position = position;
    }

    @Override
    public String value() {
        final String letterFirst = phrase.substring(position, position + 1);
        final String letterSecond = phrase.substring(position + 1, position + 2);
        return phrase.substring(0, position) +
                letterSecond +
                letterFirst +
                phrase.substring(position + 2);
    }
}

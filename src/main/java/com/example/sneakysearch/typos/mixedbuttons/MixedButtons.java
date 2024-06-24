package com.example.sneakysearch.typos.mixedbuttons;

import com.example.sneakysearch.typos.WordWithTypo;

public final class MixedButtons implements WordWithTypo {
    final String word;
    final int position;

    public MixedButtons(String word, int position) {
        this.word = word;
        this.position = position;
    }

    @Override
    public String value() {
        final String letterFirst = word.substring(position, position + 1);
        final String letterSecond = word.substring(position + 1, position + 2);
        return word.substring(0, position) +
                letterSecond +
                letterFirst +
                word.substring(position + 2);
    }
}

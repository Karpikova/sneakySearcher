package com.example.sneakysearch.typos.missedbutton;

import com.example.sneakysearch.typos.Typos;

import java.util.HashSet;
import java.util.Set;

public final class MissedInnerButtonTypos implements Typos {
    private final String phrase;

    public MissedInnerButtonTypos(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        for (int i = 1; i < phrase.length() - 1; i++) {
            typoWords.add(new MissedInnerButton(phrase, i).value());
        }
        return typoWords;
    }
}

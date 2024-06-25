package com.example.sneakysearch.typos.mixedbuttons;

import com.example.sneakysearch.typos.Typos;

import java.util.HashSet;
import java.util.Set;

public final class MixedButtonsTypos implements Typos {
    private final String phrase;

    public MixedButtonsTypos(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        for (int i = 0; i < phrase.length() - 1; i++) {
            typoWords.add(new MixedButtons(phrase, i).value());
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

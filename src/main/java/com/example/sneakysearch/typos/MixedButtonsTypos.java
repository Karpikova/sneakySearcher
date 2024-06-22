package com.example.sneakysearch.typos;

import com.example.sneakysearch.typos.typo.MixedButtons;

import java.util.HashSet;
import java.util.Set;

public final class MixedButtonsTypos implements Typos {
    private final String word;

    public MixedButtonsTypos(String word) {
        this.word = word;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        for (int i = 0; i < word.length() - 1; i++) {
            typoWords.add(new MixedButtons(word, i).value());
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

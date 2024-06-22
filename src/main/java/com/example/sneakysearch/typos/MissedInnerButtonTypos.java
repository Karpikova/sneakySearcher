package com.example.sneakysearch.typos;

import com.example.sneakysearch.typos.typo.MissedInnerButton;

import java.util.HashSet;
import java.util.Set;

public final class MissedInnerButtonTypos implements Typos {
    private final String word;

    public MissedInnerButtonTypos(String word) {
        this.word = word;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        for (int i = 1; i < word.length() - 1; i++) {
            typoWords.add(new MissedInnerButton(word, i).value());
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

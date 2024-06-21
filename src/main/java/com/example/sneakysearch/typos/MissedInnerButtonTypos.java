package com.example.sneakysearch.typos;

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
        final StringBuilder wordSb = new StringBuilder();
        for (int i = 1; i < word.length() - 1; i++) {
            new Typos.Smart().resetStringBuilder(wordSb, word);
            typoWords.add(word.substring(0, i) + word.substring(i + 1));
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

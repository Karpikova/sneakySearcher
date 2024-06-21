package com.example.sneakysearch.typos;

import java.util.HashSet;
import java.util.Set;

public final class DoubleButtonTypos implements Typos {

    private final String word;

    public DoubleButtonTypos(String word) {
        this.word = word;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        final StringBuilder wordSb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            new Smart().resetStringBuilder(wordSb, word);
            final String letter = word.substring(i, i + 1);
            typoWords.add(wordSb.insert(i, letter).toString());
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

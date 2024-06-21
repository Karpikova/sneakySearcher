package com.example.sneakysearch.typos;

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
            final String letterFirst = word.substring(i, i + 1);
            final String letterSecond = word.substring(i + 1, i + 2);
            typoWords.add(word.substring(0, i) +
                    letterSecond +
                    letterFirst +
                    word.substring(i + 2));
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

package com.example.sneakysearch.typos;

import java.util.HashSet;
import java.util.Set;

public final class MixedButtonsTypos implements Typos {
    private final String initialWord;

    public MixedButtonsTypos(String initialWord) {
        this.initialWord = initialWord;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        for (int i = 0; i < initialWord.length() - 1; i++) {
            final String letterFirst = initialWord.substring(i, i + 1);
            final String letterSecond = initialWord.substring(i + 1, i + 2);
            typoWords.add(initialWord.substring(0, i) +
                    letterSecond +
                    letterFirst +
                    initialWord.substring(i + 2));
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

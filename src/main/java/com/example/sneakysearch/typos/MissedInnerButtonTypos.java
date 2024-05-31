package com.example.sneakysearch.typos;

import java.util.HashSet;
import java.util.Set;

public final class MissedInnerButtonTypos implements Typos {
    private final String initialWord;

    public MissedInnerButtonTypos(String initialWord) {
        this.initialWord = initialWord;
    }

    @Override
    public Set<String> value() {
        Set<String> typoWords = new HashSet<>();
        StringBuilder wordSb = new StringBuilder();
        for (int i = 1; i < initialWord.length()-1; i++) {
            new Typos.Smart().resetStringBuilder(wordSb, initialWord);
            typoWords.add(new StringBuilder()
                    .append(initialWord.substring(0, i)).append(initialWord.substring(i + 1)).toString());
        }
        typoWords.stream().forEach(a -> System.out.println(a));
        return typoWords;
    }
}

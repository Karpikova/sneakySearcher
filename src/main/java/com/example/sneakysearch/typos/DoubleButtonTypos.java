package com.example.sneakysearch.typos;

import java.util.HashSet;
import java.util.Set;

public final class DoubleButtonTypos implements Typos {

    private final String initialWord;

    public DoubleButtonTypos(String initialWord) {
        this.initialWord = initialWord;
    }

    @Override
    public Set<String> value() {
        Set<String> typoWords = new HashSet<>();
        StringBuilder wordSb = new StringBuilder();
        for (int i = 0; i < initialWord.length(); i++) {
            new Smart().resetStringBuilder(wordSb, initialWord);
            String letter = initialWord.substring(i, i + 1);
            typoWords.add(wordSb.insert(i, letter).toString());
        }
        typoWords.stream().forEach(a -> System.out.println(a));
        return typoWords;
    }
}

package com.example.sneakysearch.typos.doublebutton;

import com.example.sneakysearch.typos.Typos;

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
        for (int i = 0; i < word.length(); i++) {
            typoWords.add(new DoubleButton(word, i).value());
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }
}

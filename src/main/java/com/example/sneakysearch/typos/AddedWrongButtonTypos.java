package com.example.sneakysearch.typos;

import com.example.sneakysearch.typos.typo.WrongButtonAfter;
import com.example.sneakysearch.typos.typo.WrongButtonBefore;
import com.example.sneakysearch.typos.typo.WrongButtonInsteadOf;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AddedWrongButtonTypos implements Typos {

    private final String word;
    private final Keyboard keyboard;

    public AddedWrongButtonTypos(String word, Keyboard keyboard) {
        this.word = word;
        this.keyboard = keyboard;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        final Map<String, List<String>> lettersWithNeighbours = keyboard.lettersWithNeighbours();
        for (int i = 0; i < word.length(); i++) {
            final List<String> neighbours = lettersWithNeighbours.getOrDefault(word.substring(i, i + 1), List.of());
            for (String neigh : neighbours) {
                typoWords.add(new WrongButtonInsteadOf(word, i, neigh).value());
                typoWords.add(new WrongButtonBefore(word, i, neigh).value());
                typoWords.add(new WrongButtonAfter(word, i, neigh).value());
            }
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }

}

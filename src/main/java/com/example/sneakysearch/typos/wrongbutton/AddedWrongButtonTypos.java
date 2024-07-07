package com.example.sneakysearch.typos.wrongbutton;

import com.example.sneakysearch.typos.Typos;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AddedWrongButtonTypos implements Typos {

    private final String phrase;
    private final Keyboard keyboard;

    public AddedWrongButtonTypos(String phrase, Keyboard keyboard) {
        this.phrase = phrase;
        this.keyboard = keyboard;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoPhrases = new HashSet<>();
        final Map<String, List<String>> lettersWithNeighbours = keyboard.lettersWithNeighbours();
        for (int i = 0; i < phrase.length(); i++) {
            final List<String> neighbours = lettersWithNeighbours.getOrDefault(phrase.substring(i, i + 1), List.of());
            for (String neigh : neighbours) {
                typoPhrases.add(new WrongButtonInsteadOf(phrase, i, neigh).value());
                typoPhrases.add(new WrongButtonBefore(phrase, i, neigh).value());
                typoPhrases.add(new WrongButtonAfter(phrase, i, neigh).value());
            }
        }
        return typoPhrases;
    }
}

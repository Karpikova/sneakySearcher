package com.example.sneakysearch.typos;

import com.example.sneakysearch.typos.wrongbutton.WrongButtonAfter;
import com.example.sneakysearch.typos.wrongbutton.WrongButtonBefore;
import com.example.sneakysearch.typos.wrongbutton.WrongButtonInsteadOf;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AddedWrongButtonTypos implements Typos {

    private final String initialWord;
    private final Keyboard keyboard;

    public AddedWrongButtonTypos(String initialWord, Keyboard keyboard) {
        this.initialWord = initialWord;
        this.keyboard = keyboard;
    }

    @Override
    public Set<String> value() {
        final Set<String> typoWords = new HashSet<>();
        final Map<String, List<String>> lettersWithNeighbours = keyboard.lettersWithNeighbours();
        for (int i = 0; i < initialWord.length(); i++) {
            final List<String> characters = lettersWithNeighbours.getOrDefault(initialWord.substring(i, i + 1), List.of());
            for (String c : characters) {
                typoWords.add(new WrongButtonInsteadOf(initialWord, i, c).value());
                typoWords.add(new WrongButtonBefore(initialWord, i, c).value());
                typoWords.add(new WrongButtonAfter(initialWord, i, c).value());
            }
        }
        typoWords.forEach(System.out::println);
        return typoWords;
    }

}

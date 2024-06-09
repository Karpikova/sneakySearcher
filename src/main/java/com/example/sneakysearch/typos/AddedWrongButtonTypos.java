package com.example.sneakysearch.typos;

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
        HashSet<String> typoWords = new HashSet<>();
        Map<String, List<String>> lettersWithNeighbours = keyboard.lettersWithNeighbours();//я тут
        for (int i = 0; i < initialWord.length(); i++) {
            List<String> characters = lettersWithNeighbours.getOrDefault(initialWord.substring(i, i + 1), List.of());
            StringBuilder wordSb = new StringBuilder();
            for (String c : characters) {
                new Typos.Smart().resetStringBuilder(wordSb, initialWord);
                typoWords.add(wordSb.replace(i, i + 1, c).toString());
                new Typos.Smart().resetStringBuilder(wordSb, initialWord);
                typoWords.add(wordSb.insert(i, c).toString());
                new Typos.Smart().resetStringBuilder(wordSb, initialWord);
                typoWords.add(wordSb.insert(i + 1, c).toString());
            }
        }
        typoWords.stream().forEach(a -> System.out.println(a));
        return typoWords;
    }

}

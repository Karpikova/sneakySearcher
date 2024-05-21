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
        Set<String> typoWords = new HashSet<>();
        for (int i = 0; i < initialWord.length() - 1; i++) {
            String letterFirst = initialWord.substring(i, i + 1);
            String letterSecond = initialWord.substring(i + 1, i + 2);
            typoWords.add(new StringBuilder()
                    .append(initialWord.substring(0, i))
                    .append(letterSecond)
                    .append(letterFirst)
                    .append(initialWord.substring(i + 2))
                    .toString());
        }
        typoWords.stream().forEach(a-> System.out.println(a));
        return typoWords;
    }
}

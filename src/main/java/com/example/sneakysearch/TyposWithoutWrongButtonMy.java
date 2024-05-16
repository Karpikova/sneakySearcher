package com.example.sneakysearch;

import java.util.HashSet;
import java.util.Set;

public class TyposWithoutWrongButtonMy implements Typos {

    private final String initialWord;

    public TyposWithoutWrongButtonMy(String initialWord) {
        this.initialWord = initialWord;
    }

    @Override
    public Set<String> value() {

        HashSet<String> typoWords = new HashSet<>();

        StringBuilder wordSb = new StringBuilder();
        for (int i = 0; i < initialWord.length(); i++) {
            new Typos.Smart().resetStringBuilder(wordSb, initialWord);
            String letter = initialWord.substring(i, i + 1);
            typoWords.add(wordSb.insert(i, letter).toString());
            typoWords.add(new StringBuilder()//проверь алгоритм
                    .append(initialWord.substring(0, i)).append(initialWord.substring(i + 1)).toString());
        }
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
        return typoWords;
    }
}

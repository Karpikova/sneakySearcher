package com.example.sneakysearch;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TyposWithWrongButtonMy implements Typos {

    private final String initialWord;
    private final Keyboard keyboard;

    public TyposWithWrongButtonMy(String initialWord, Keyboard keyboard) {
        this.initialWord = initialWord;
        this.keyboard = keyboard;
    }

    @Override
    public Set<String> value() {
        HashSet<String> typoWords = new HashSet<>();
        Map<String, List<String>> keyboardValue = keyboard.value();
        for (int i = 0; i < initialWord.length(); i++) {
            List<String> characters = keyboardValue.get(initialWord.substring(i, i + 1));
            if (characters != null) {
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
        }
        return typoWords;
    }

}

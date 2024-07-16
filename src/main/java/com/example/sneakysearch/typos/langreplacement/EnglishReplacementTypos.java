package com.example.sneakysearch.typos.langreplacement;

import com.example.sneakysearch.typos.Typos;
import com.example.sneakysearch.typos.wrongbutton.Keyboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class EnglishReplacementTypos implements Typos {
    private static final Logger LOGGER = LogManager.getLogger(EnglishReplacementTypos.class);
    private final String phrase;
    private final Keyboard keyboard;

    public EnglishReplacementTypos(String phrase, Keyboard keyboard) {
        this.phrase = phrase;
        this.keyboard = keyboard;
    }

    @Override
    public Set<String> value() { //TODO проверка на слишком длинное слово. А надо ли? Вопрос открыт.
        final Map<Character, String> lettersWithEngAnalogues = keyboard.lettersWithEnglishAnalogues();
        final Boolean[] whetherWordLetterHasEngAnalogue = phrase.chars().mapToObj(lt -> (char) lt).
                map(lettersWithEngAnalogues::containsKey).toArray(Boolean[]::new);
        final int countWordLettersHavingEngAnalogue = (int) Arrays.stream(whetherWordLetterHasEngAnalogue)
                .filter(Boolean::booleanValue).count();
        final List<Boolean[]> sBinaryDigits = new SequentialBinaryDigitsMy(countWordLettersHavingEngAnalogue)
                .withoutZero();
        final List<Boolean[]> templates = templates(sBinaryDigits, whetherWordLetterHasEngAnalogue);
        return typoWordsCreatedByTemplates(templates, lettersWithEngAnalogues);
    }

    private Set<String> typoWordsCreatedByTemplates(List<Boolean[]> templates,
                                                    Map<Character, String> lettersWithEnglishAnalogues) {
        final Set<String> typoWords = new HashSet<>();
        final StringBuilder sb = new StringBuilder();
        for (Boolean[] template : templates) {
            for (int i = 0; i < template.length; i++) {
                final Character originalLetter = phrase.substring(i, i + 1).charAt(0);
                sb.append(template[i] ? lettersWithEnglishAnalogues.get(originalLetter) : originalLetter);
            }
            typoWords.add(sb.toString());
            sb.setLength(0);
        }
        LOGGER.info("Замен с английскими буквами — " + typoWords.size());
        return typoWords;
    }

    private List<Boolean[]> templates(List<Boolean[]> sequentialBinaryDigits, Boolean[] whetherLetterHasEngAnalogue) {
        final List<Boolean[]> boolTemplates = new ArrayList<>();
        for (Boolean[] sbd : sequentialBinaryDigits) {
            final Boolean[] bt = new Boolean[phrase.length()];
            int j = 0;
            for (int i = 0; i < bt.length; i++) {
                bt[i] = whetherLetterHasEngAnalogue[i] ? sbd[j++] : false; //заменяем только единицы
            }
            boolTemplates.add(bt);
        }
        return boolTemplates;
    }
}

package com.example.sneakysearch.typos;

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
    private final String word;
    private final Keyboard keyboard;

    public EnglishReplacementTypos(String word, Keyboard keyboard) {
        this.word = word;
        this.keyboard = keyboard;
    }

    @Override
    public Set<String> value() { //TODO проверка на слишком длинное слово. Да и вообще на то, что на вход пришло одно слово
        final Map<Character, String> lettersWithEnglishAnalogues = keyboard.lettersWithEnglishAnalogues();
        final Boolean[] lettersHavingEngAnalogue = word.chars().mapToObj(lt -> (char) lt).
                map(lettersWithEnglishAnalogues::containsKey).toArray(Boolean[]::new);
        final List<Boolean[]> shortTemplatesForEngAnalogueLetters = shortTemplatesForEngAnalogueLetters(lettersHavingEngAnalogue);
        final List<Boolean[]> templates = templates(shortTemplatesForEngAnalogueLetters, lettersHavingEngAnalogue);
        final Set<String> typoWords = typoWordsCreatedByTemplates(templates, lettersWithEnglishAnalogues);
        return typoWords;
    }

    private Set<String> typoWordsCreatedByTemplates(List<Boolean[]> templates, Map<Character, String> lettersWithEnglishAnalogues) {
        final Set<String> typoWords = new HashSet<>();
        final StringBuilder wordSb = new StringBuilder();
        for (Boolean[] template : templates) {
            for (int i = 0; i < template.length; i++) {
                final String originalLetter = word.substring(i, i + 1);
                final Character originalLetterChar = originalLetter.charAt(0);
                wordSb.append(
                        template[i] ? lettersWithEnglishAnalogues.get(originalLetterChar) : originalLetter
                );
            }
            typoWords.add(wordSb.toString());
            wordSb.setLength(0);
        }
        LOGGER.info("Замен с английскими буквами — " + typoWords.size());
        return typoWords;
    }

    private List<Boolean[]> templates(List<Boolean[]> sequenceShortBoolTemplates, Boolean[] lettersHavingEngAnalogue) {
        final List<Boolean[]> boolTemplates = new ArrayList<>();
        for (Boolean[] ssbt : sequenceShortBoolTemplates) {
            final Boolean[] bt = new Boolean[word.length()];
            int j = 0;
            for (int i = 0; i < bt.length; i++) {
                bt[i] = lettersHavingEngAnalogue[i] ? ssbt[j++] : false; //заменяем только единицы
            }
            boolTemplates.add(bt);
        }
        return boolTemplates;
    }


    private List<Boolean[]> shortTemplatesForEngAnalogueLetters(Boolean[] whatLettersHaveEngAnalogue) {
        final int countLettersHavingEngAnalogue = (int) Arrays.stream(whatLettersHaveEngAnalogue)
                .filter(Boolean::booleanValue).count();
        final List<Boolean[]> boolTemplates = new ArrayList<>();
        for (int i = 1; i < Math.pow(2, countLettersHavingEngAnalogue); i++) {
            final char[] resultInChar = addLeadZeros(Integer.toBinaryString(i).toCharArray(), countLettersHavingEngAnalogue);
            final Boolean[] resultInBool = charToBoolean(resultInChar);
            boolTemplates.add(resultInBool);
        }
        return boolTemplates;
    }

    private Boolean[] charToBoolean(char[] arrayWithoutLeadZeros) {
        final int size = arrayWithoutLeadZeros.length;
        final Boolean[] boolArray = new Boolean[size];
        for (int i = 0; i < size; i++) {
            boolArray[i] = arrayWithoutLeadZeros[i] != '0';
        }
        return boolArray;
    }

    private char[] addLeadZeros(char[] arrayWithoutLeadZeros, int countLettersHavingEngAnalogue) {
        final char[] arrayWithLeadZeros = new char[countLettersHavingEngAnalogue];
        final int diff = countLettersHavingEngAnalogue - arrayWithoutLeadZeros.length;
        for (int i = 0; i < diff; i++) {
            arrayWithLeadZeros[i] = '0';
        }
        System.arraycopy(arrayWithoutLeadZeros, 0, arrayWithLeadZeros, diff, countLettersHavingEngAnalogue - diff);
        return arrayWithLeadZeros;
    }

}

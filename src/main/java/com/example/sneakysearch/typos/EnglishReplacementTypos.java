package com.example.sneakysearch.typos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class EnglishReplacementTypos implements Typos {
    private final String initialWord;
    private final Keyboard keyboard;

    public EnglishReplacementTypos(String initialWord, Keyboard keyboard) {
        this.initialWord = initialWord;
        this.keyboard = keyboard;
    }

    @Override
    public Set<String> value() { //TODO проверка на слишком длинное слово. Да и вообще на то, что на вход пришло одно слово
        Map<Character, String> lettersWithEnglishAnalogues = keyboard.lettersWithEnglishAnalogues();
        Boolean[] lettersHavingEngAnalogue = initialWord.chars().mapToObj(lt -> (char) lt).
                map(lt -> lettersWithEnglishAnalogues.containsKey(lt)).toArray(Boolean[]::new);
        List<Boolean[]> shortTemplatesForEngAnalogueLetters = shortTemplatesForEngAnalogueLetters(lettersHavingEngAnalogue);
        List<Boolean[]> templates = templates(shortTemplatesForEngAnalogueLetters, lettersHavingEngAnalogue);
        Set<String> typoWords = typoWordsCreatedByTemplates(templates, lettersWithEnglishAnalogues);
        return typoWords;
    }

    private Set<String> typoWordsCreatedByTemplates(List<Boolean[]> templates, Map<Character, String> lettersWithEnglishAnalogues) {
        Set<String> typoWords = new HashSet<>();
        StringBuilder wordSb = new StringBuilder();
        for (Boolean[] template : templates) {
            for (int i = 0; i < template.length; i++) {
                String originalLetter = initialWord.substring(i, i+1);
                Character originalLetterChar = originalLetter.charAt(0);
                wordSb.append(
                        template[i] == false ? originalLetter : lettersWithEnglishAnalogues.get(originalLetterChar)
                );
            }
            typoWords.add(wordSb.toString());
            wordSb.setLength(0);
        }
        System.out.println("Замен с английскими буквами — " + typoWords.size());
        return typoWords;
    }

    private List<Boolean[]> templates(List<Boolean[]> sequenceShortBoolTemplates, Boolean[] lettersHavingEngAnalogue) {
        List<Boolean[]> boolTemplates = new ArrayList<>();
        for (Boolean[] ssbt : sequenceShortBoolTemplates) {
            Boolean[] bt = new Boolean[initialWord.length()];
            int j = 0;
            for (int i = 0; i < bt.length; i++) {
                bt[i] = lettersHavingEngAnalogue[i] == true ? ssbt[j++] : false; //заменяем только единицы
            }
            boolTemplates.add(bt);
        }
        return boolTemplates;
    }


    private List<Boolean[]> shortTemplatesForEngAnalogueLetters(Boolean[] whatLettersHaveEngAnalogue) {
        int countLettersHavingEngAnalogue = (int) Arrays.stream(whatLettersHaveEngAnalogue)
                .filter(Boolean::booleanValue).count();
        List<Boolean[]> boolTemplates = new ArrayList<>();
        for (int i = 1; i < Math.pow(2, countLettersHavingEngAnalogue); i++) {
            char[] resultInChar = addLeadZeros(Integer.toBinaryString(i).toCharArray(), countLettersHavingEngAnalogue);
            Boolean[] resultInBool = charToBoolean(resultInChar);
            boolTemplates.add(resultInBool);
        }
        return boolTemplates;
    }

    private Boolean[] charToBoolean(char[] arrayWithoutLeadZeros) {
        int size = arrayWithoutLeadZeros.length;
        Boolean[] boolArray = new Boolean[size];
        for (int i = 0; i < size; i++) {
            boolArray[i] = arrayWithoutLeadZeros[i] == '0' ? false : true;
        }
        return boolArray;
    }

    private char[] addLeadZeros(char[] arrayWithoutLeadZeros, int countLettersHavingEngAnalogue) {
        char[] arrayWithLeadZeros = new char[countLettersHavingEngAnalogue];
        int diff = countLettersHavingEngAnalogue - arrayWithoutLeadZeros.length;
        for (int i = 0; i < diff; i++) {
            arrayWithLeadZeros[i] = '0';
        }
        for (int i = diff; i < countLettersHavingEngAnalogue; i++) {
            arrayWithLeadZeros[i] = arrayWithoutLeadZeros[i - diff];
        }
        return arrayWithLeadZeros;
    }

}

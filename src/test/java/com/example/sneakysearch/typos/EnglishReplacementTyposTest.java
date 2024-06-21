package com.example.sneakysearch.typos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnglishReplacementTyposTest {
    @Test
    void value() {
        EnglishReplacementTypos dbt = new EnglishReplacementTypos("кот", new RussianKeyboard());
        Set<String> value = dbt.value();
        assertEquals(3, value.size());
        Assertions.assertTrue(value.contains("kот"));
        Assertions.assertTrue(value.contains("кoт"));
        Assertions.assertTrue(value.contains("koт"));
    }
}
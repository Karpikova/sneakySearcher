package com.example.sneakysearch.typos;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

class EnglishReplacementTyposTest {
    @Test
    void value() {
        EnglishReplacementTypos dbt = new EnglishReplacementTypos("кот", new RussianKeyboard());
        Set<String> value = dbt.value();
        Assert.assertEquals(3, value.size());
        Assert.assertTrue(value.contains("kот"));
        Assert.assertTrue(value.contains("кoт"));
        Assert.assertTrue(value.contains("koт"));
    }
}
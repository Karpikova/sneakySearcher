package com.example.sneakysearch;

import com.example.sneakysearch.typos.MixedButtonsTypos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class MixedButtonsTyposTest {
    @Test
    void value(){
        MixedButtonsTypos mixedButtonsTypos = new MixedButtonsTypos("кот");
        Set<String> value = mixedButtonsTypos.value();
        Assertions.assertEquals(2, value.size());
        Assertions.assertTrue(value.contains("окт"));
        Assertions.assertTrue(value.contains("кто"));
    }
}
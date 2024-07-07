package com.example.sneakysearch;

import com.example.sneakysearch.typos.mixedbuttons.MixedButtonsTypos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MixedButtonsTyposTest {

    protected Set<String> expected = new HashSet<>(Arrays.asList("окт", "кто"));
    @Test
    void value(){
        MixedButtonsTypos mixedButtonsTypos = new MixedButtonsTypos("кот");
        Set<String> value = mixedButtonsTypos.value();
        Assertions.assertEquals(expected.size(), value.size());
        expected.forEach(q-> Assertions.assertTrue(value.contains(q)));
    }
}
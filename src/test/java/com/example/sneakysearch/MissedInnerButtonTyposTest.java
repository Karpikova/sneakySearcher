package com.example.sneakysearch;

import com.example.sneakysearch.typos.missedbutton.MissedInnerButtonTypos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissedInnerButtonTyposTest {
    protected Set<String> expected = new HashSet<>(Arrays.asList("кт"));
    @Test
    void value() {
        MissedInnerButtonTypos mbt = new MissedInnerButtonTypos("кот");
        Set<String> value = mbt.value();
        assertEquals(expected.size(), value.size());
        expected.forEach(q-> Assertions.assertTrue(value.contains(q)));
    }
}
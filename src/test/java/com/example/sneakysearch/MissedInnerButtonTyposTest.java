package com.example.sneakysearch;

import com.example.sneakysearch.typos.missedbutton.MissedInnerButtonTypos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissedInnerButtonTyposTest {
    @Test
    void value() {
        MissedInnerButtonTypos mbt = new MissedInnerButtonTypos("кот");
        Set<String> value = mbt.value();
        assertEquals(1, value.size());
        Assertions.assertTrue(value.contains("кт"));
    }
}
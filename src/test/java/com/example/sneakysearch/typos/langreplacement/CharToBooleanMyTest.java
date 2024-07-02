package com.example.sneakysearch.typos.langreplacement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharToBooleanMyTest {
    @Test
    void value() {
        char[] charArray = {'1', '0', '0'};
        CharToBoolean ctb = new CharToBooleanMy(charArray);
        Boolean[] value = ctb.value();
        Assertions.assertTrue(value[0]);
        Assertions.assertFalse(value[1]);
        Assertions.assertFalse(value[2]);
    }
}
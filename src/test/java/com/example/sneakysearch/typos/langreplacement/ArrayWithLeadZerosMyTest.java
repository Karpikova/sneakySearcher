package com.example.sneakysearch.typos.langreplacement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayWithLeadZerosMyTest {
    @Test
    void value() {
        char[] charArray = {'1', '0', '0'};
        ArrayWithLeadZeros awlz = new ArrayWithLeadZerosMy(charArray, 5);
        char[] value = awlz.value();
        Assertions.assertEquals(value[0], '0');
        Assertions.assertEquals(value[1], '0');
        Assertions.assertEquals(value[2], '1');
        Assertions.assertEquals(value[3], '0');
        Assertions.assertEquals(value[4], '0');
    }
}
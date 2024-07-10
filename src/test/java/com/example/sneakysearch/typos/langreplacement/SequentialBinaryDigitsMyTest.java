package com.example.sneakysearch.typos.langreplacement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SequentialBinaryDigitsMyTest {

    @Test
    void withoutZero() {
        Boolean[] el1 = { false, false, true  };
        Boolean[] el2 = { false, true,  false };
        Boolean[] el3 = { false, true,  true  };
        Boolean[] el4 = { true,  false, false };
        Boolean[] el5 = { true,  false, true  };
        Boolean[] el6 = { true,  true,  false };
        Boolean[] el7 = { true,  true,  true  };
        SequentialBinaryDigits sbd = new SequentialBinaryDigitsMy( 3);
        List<Boolean[]> value = sbd.withoutZero();
        Assertions.assertArrayEquals(value.get(0), el1);
        Assertions.assertArrayEquals(value.get(1), el2);
        Assertions.assertArrayEquals(value.get(2), el3);
        Assertions.assertArrayEquals(value.get(3), el4);
        Assertions.assertArrayEquals(value.get(4), el5);
        Assertions.assertArrayEquals(value.get(5), el6);
        Assertions.assertArrayEquals(value.get(6), el7);
    }
}
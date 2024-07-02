package com.example.sneakysearch.typos.langreplacement;

import java.util.List;
import java.util.stream.IntStream;

public class SequentialBinaryDigitsMy implements SequentialBinaryDigits {
    private final int length;

    public SequentialBinaryDigitsMy(int length) {
        this.length = length;
    }

    @Override
    public List<Boolean[]> withoutZero() {
        return IntStream.range(1, (int) Math.pow(2, length))
                .mapToObj(i -> Integer.toBinaryString(i).toCharArray())
                .map(f -> new ArrayWithLeadZerosMy(f, length).value())
                .map(f -> new CharToBooleanMy(f).value()).toList();
    }
}

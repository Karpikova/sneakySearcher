package com.example.sneakysearch.typos.langreplacement;

import java.util.List;
import java.util.stream.IntStream;

public class SequentialBinaryDigitsFromOneToParamMy implements SequentialBinaryDigitsFromOneToParam {
    private final int param;

    public SequentialBinaryDigitsFromOneToParamMy(int param) {
        this.param = param;
    }

    @Override
    public List<Boolean[]> value() {
        return IntStream.range(1, (int) Math.pow(2, param))
                .mapToObj(i -> Integer.toBinaryString(i).toCharArray())
                .map(f -> new ArrayWithLeadZerosMy(f, param).value())
                .map(f -> new CharToBooleanMy(f).value()).toList();
    }
}

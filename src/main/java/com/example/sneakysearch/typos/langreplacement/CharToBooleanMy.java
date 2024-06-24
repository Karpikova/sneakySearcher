package com.example.sneakysearch.typos.langreplacement;

import java.util.stream.IntStream;

public final class CharToBooleanMy implements CharToBoolean {
    private final char[] chars;

    public CharToBooleanMy(char[] chars) {
        this.chars = chars;
    }

    @Override
    public Boolean[] value() {
        return IntStream.range(0, chars.length)
                .mapToObj(idx -> chars[idx] != '0').toArray(Boolean[]::new);
    }
}

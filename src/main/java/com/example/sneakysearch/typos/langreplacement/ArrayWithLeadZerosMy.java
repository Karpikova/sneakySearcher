package com.example.sneakysearch.typos.langreplacement;

public final class ArrayWithLeadZerosMy implements ArrayWithLeadZeros {
    private final char[] arrayWithoutLeadZeros;
    private final int goalLength;

    public ArrayWithLeadZerosMy(char[] arrayWithoutLeadZeros, int goalLength) {
        this.arrayWithoutLeadZeros = arrayWithoutLeadZeros;
        this.goalLength = goalLength;
    }

    @Override
    public char[] value() {
        final char[] arrayWithLeadZeros = new char[goalLength];
        final int diff = goalLength - arrayWithoutLeadZeros.length;
        for (int i = 0; i < diff; i++) {
            arrayWithLeadZeros[i] = '0';
        }
        System.arraycopy(arrayWithoutLeadZeros, 0, arrayWithLeadZeros, diff, goalLength - diff);
        return arrayWithLeadZeros;
    }
}

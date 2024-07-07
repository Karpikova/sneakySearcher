package com.example.sneakysearch;

import com.example.sneakysearch.typos.doublebutton.DoubleButtonTypos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleButtonTyposTest {
    protected Set<String> expected = new HashSet<>(Arrays.asList("ккот", "коот", "котт"));

    @Test
    void value() {
        DoubleButtonTypos dbt = new DoubleButtonTypos("кот");
        Set<String> value = dbt.value();
        assertEquals(expected.size(), value.size());
        expected.forEach(q-> Assertions.assertTrue(value.contains(q)));
    }
}
package com.example.sneakysearch;

import com.example.sneakysearch.typos.doublebutton.DoubleButtonTypos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleButtonTyposTest {
    @Test
    void value() {
        DoubleButtonTypos dbt = new DoubleButtonTypos("кот");
        Set<String> value = dbt.value();
        assertEquals(3, value.size());
        Assertions.assertTrue(value.contains("ккот"));
        Assertions.assertTrue(value.contains("коот"));
        Assertions.assertTrue(value.contains("котт"));
    }
}
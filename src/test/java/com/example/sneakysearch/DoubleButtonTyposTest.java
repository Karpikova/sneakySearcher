package com.example.sneakysearch;

import com.example.sneakysearch.typos.DoubleButtonTypos;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

class DoubleButtonTyposTest {
    @Test
    void value() {
        DoubleButtonTypos dbt = new DoubleButtonTypos("кот");
        Set<String> value = dbt.value();
        Assert.assertEquals(3, value.size());
        Assert.assertTrue(value.contains("ккот"));
        Assert.assertTrue(value.contains("коот"));
        Assert.assertTrue(value.contains("котт"));
    }
}
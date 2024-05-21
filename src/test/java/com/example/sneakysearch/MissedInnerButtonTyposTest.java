package com.example.sneakysearch;

import com.example.sneakysearch.typos.MissedInnerButtonTypos;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

class MissedInnerButtonTyposTest {
    @Test
    void value() {
        MissedInnerButtonTypos mbt = new MissedInnerButtonTypos("кот");
        Set<String> value = mbt.value();
        Assert.assertEquals(1, value.size());
        Assert.assertTrue(value.contains("кт"));
    }
}
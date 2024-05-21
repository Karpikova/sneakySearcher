package com.example.sneakysearch;

import com.example.sneakysearch.typos.MixedButtonsTypos;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

class MixedButtonsTyposTest {
    @Test
    void value(){
        MixedButtonsTypos mixedButtonsTypos = new MixedButtonsTypos("кот");
        Set<String> value = mixedButtonsTypos.value();
        Assert.assertEquals(2, value.size());
        Assert.assertTrue(value.contains("окт"));
        Assert.assertTrue(value.contains("кто"));
    }
}
package com.example.sneakysearch;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

class TyposWithoutWrongButtonTest {

    @Test
    void value(){
        TyposWithoutWrongButton typosWithoutWrongButton = new TyposWithoutWrongButton("кот");
        Set<String> value = typosWithoutWrongButton.value();
        Assert.assertEquals(8, value.size());
        Assert.assertTrue(value.contains("от"));
        Assert.assertTrue(value.contains("кт"));
        Assert.assertTrue(value.contains("ко"));

        Assert.assertTrue(value.contains("ккот"));
        Assert.assertTrue(value.contains("коот"));
        Assert.assertTrue(value.contains("котт"));

        Assert.assertTrue(value.contains("окт"));
        Assert.assertTrue(value.contains("кто"));

    }
}
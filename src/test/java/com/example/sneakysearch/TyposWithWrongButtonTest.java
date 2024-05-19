package com.example.sneakysearch;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class TyposWithWrongButtonTest {

    @Test
    void value(){
        TyposWithWrongButton typosWithWrongButton = new TyposWithWrongButton("кот", new RussianKeyboard());
        Set<String> value = typosWithWrongButton.value();
        Assert.assertEquals(40, value.size()); //дублей нет
        Assert.assertTrue(value.contains("еот"));
        Assert.assertTrue(value.contains("вот"));
        Assert.assertTrue(value.contains("уот"));

        Assert.assertTrue(value.contains("екот"));
        Assert.assertTrue(value.contains("вкот"));
        Assert.assertTrue(value.contains("укот"));

        Assert.assertTrue(value.contains("кеот"));
        Assert.assertTrue(value.contains("квот"));
        Assert.assertTrue(value.contains("куот"));
    }
}
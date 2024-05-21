package com.example.sneakysearch;

import com.example.sneakysearch.typos.AddedWrongButtonTypos;
import com.example.sneakysearch.typos.RussianKeyboard;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class AddedWrongButtonTyposTest {

    @Test
    void value(){
        AddedWrongButtonTypos addedWrongButtonTypos = new AddedWrongButtonTypos("кот", new RussianKeyboard());
        Set<String> value = addedWrongButtonTypos.value();
        Assert.assertEquals(40, value.size()); //а не 42, за счет двух дублей.
        //"е", "а", "в", "у"
        Assert.assertTrue(value.contains("еот"));
        Assert.assertTrue(value.contains("аот"));
        Assert.assertTrue(value.contains("вот"));
        Assert.assertTrue(value.contains("уот"));

        Assert.assertTrue(value.contains("кеот"));
        Assert.assertTrue(value.contains("каот"));
        Assert.assertTrue(value.contains("квот"));
        Assert.assertTrue(value.contains("куот"));

        Assert.assertTrue(value.contains("екот"));
        Assert.assertTrue(value.contains("акот"));
        Assert.assertTrue(value.contains("вкот"));
        Assert.assertTrue(value.contains("укот"));

        //г", "ш", "л", "ь", "т", "р"
        Assert.assertTrue(value.contains("кгот"));
        Assert.assertTrue(value.contains("кшот"));
        Assert.assertTrue(value.contains("клот"));
        Assert.assertTrue(value.contains("кьот"));
        Assert.assertTrue(value.contains("ктот"));
        Assert.assertTrue(value.contains("крот"));

        Assert.assertTrue(value.contains("кгт"));
        Assert.assertTrue(value.contains("кшт"));
        Assert.assertTrue(value.contains("клт"));
        Assert.assertTrue(value.contains("кьт"));
        Assert.assertTrue(value.contains("ктт"));
        Assert.assertTrue(value.contains("крт"));

        Assert.assertTrue(value.contains("когт"));
        Assert.assertTrue(value.contains("кошт"));
        Assert.assertTrue(value.contains("колт"));
        Assert.assertTrue(value.contains("коьт")); //дважды
        Assert.assertTrue(value.contains("котт"));
        Assert.assertTrue(value.contains("корт")); //дважды

        //"и", "р", "о", "ь"
        Assert.assertTrue(value.contains("коит"));
        Assert.assertTrue(value.contains("корт")); //дважды
        Assert.assertTrue(value.contains("коот"));
        Assert.assertTrue(value.contains("коьт")); //дважды

        Assert.assertTrue(value.contains("кои"));
        Assert.assertTrue(value.contains("кор"));
        Assert.assertTrue(value.contains("коо"));
        Assert.assertTrue(value.contains("коь"));

        Assert.assertTrue(value.contains("коти"));
        Assert.assertTrue(value.contains("котр"));
        Assert.assertTrue(value.contains("кото"));
        Assert.assertTrue(value.contains("коть"));

    }
}
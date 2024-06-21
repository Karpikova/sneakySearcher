package com.example.sneakysearch;

import com.example.sneakysearch.typos.RussianKeyboard;
import com.example.sneakysearch.typos.wrongbutton.AddedWrongButtonTypos;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddedWrongButtonTyposTest {

    @Test
    void value(){
        AddedWrongButtonTypos addedWrongButtonTypos = new AddedWrongButtonTypos("кот", new RussianKeyboard());
        Set<String> value = addedWrongButtonTypos.value();
        assertEquals(40, value.size()); //а не 42, за счет двух дублей.
        //"е", "а", "в", "у"
        assertTrue(value.contains("еот"));
        assertTrue(value.contains("аот"));
        assertTrue(value.contains("вот"));
        assertTrue(value.contains("уот"));

        assertTrue(value.contains("кеот"));
        assertTrue(value.contains("каот"));
        assertTrue(value.contains("квот"));
        assertTrue(value.contains("куот"));

        assertTrue(value.contains("екот"));
        assertTrue(value.contains("акот"));
        assertTrue(value.contains("вкот"));
        assertTrue(value.contains("укот"));

        //г", "ш", "л", "ь", "т", "р"
        assertTrue(value.contains("кгот"));
        assertTrue(value.contains("кшот"));
        assertTrue(value.contains("клот"));
        assertTrue(value.contains("кьот"));
        assertTrue(value.contains("ктот"));
        assertTrue(value.contains("крот"));

        assertTrue(value.contains("кгт"));
        assertTrue(value.contains("кшт"));
        assertTrue(value.contains("клт"));
        assertTrue(value.contains("кьт"));
        assertTrue(value.contains("ктт"));
        assertTrue(value.contains("крт"));

        assertTrue(value.contains("когт"));
        assertTrue(value.contains("кошт"));
        assertTrue(value.contains("колт"));
        assertTrue(value.contains("коьт")); //дважды
        assertTrue(value.contains("котт"));
        assertTrue(value.contains("корт")); //дважды

        //"и", "р", "о", "ь"
        assertTrue(value.contains("коит"));
        assertTrue(value.contains("корт")); //дважды
        assertTrue(value.contains("коот"));
        assertTrue(value.contains("коьт")); //дважды

        assertTrue(value.contains("кои"));
        assertTrue(value.contains("кор"));
        assertTrue(value.contains("коо"));
        assertTrue(value.contains("коь"));

        assertTrue(value.contains("коти"));
        assertTrue(value.contains("котр"));
        assertTrue(value.contains("кото"));
        assertTrue(value.contains("коть"));

    }
}
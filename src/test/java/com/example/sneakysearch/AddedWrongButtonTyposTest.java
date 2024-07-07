package com.example.sneakysearch;

import com.example.sneakysearch.typos.wrongbutton.AddedWrongButtonTypos;
import com.example.sneakysearch.typos.wrongbutton.RussianKeyboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddedWrongButtonTyposTest {

    protected Set<String> expected = new HashSet<>(Arrays.asList("еот", "аот", "вот", "уот",
            "кеот", "каот", "квот", "куот",
            "екот", "акот", "вкот", "укот",
            "кгот", "кшот", "клот", "кьот", "ктот", "крот",
            "кгт", "кшт", "клт", "кьт", "ктт", "крт",
            "когт", "кошт", "колт", "коьт", "котт", "корт",
            "коит", "коот",
            "кои", "кор", "коо", "коь",
            "коти", "котр", "кото", "коть"));
    @Test
    void value(){
        AddedWrongButtonTypos addedWrongButtonTypos = new AddedWrongButtonTypos("кот", new RussianKeyboard());
        Set<String> value = addedWrongButtonTypos.value();
        assertEquals(expected.size(), value.size()); //40, а не 42, за счет двух дублей - коьт и корт
        expected.forEach(q-> Assertions.assertTrue(value.contains(q)));
    }
}
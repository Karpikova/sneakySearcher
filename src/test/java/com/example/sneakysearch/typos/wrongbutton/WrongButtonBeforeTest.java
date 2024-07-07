package com.example.sneakysearch.typos.wrongbutton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrongButtonBeforeTest {
    protected String expected = "маолоко";
    @Test
    void value(){
        WrongButtonBefore wb = new WrongButtonBefore("молоко", 1, "а");
        String value = wb.value();
        assertEquals(expected, value);
    }
}
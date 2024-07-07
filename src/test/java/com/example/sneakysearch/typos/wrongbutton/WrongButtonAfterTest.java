package com.example.sneakysearch.typos.wrongbutton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrongButtonAfterTest {
    protected String expected = "моалоко";
    @Test
    void value(){
        WrongButtonAfter wb = new WrongButtonAfter("молоко", 1, "а");
        String value = wb.value();
        assertEquals(expected, value);
    }
}
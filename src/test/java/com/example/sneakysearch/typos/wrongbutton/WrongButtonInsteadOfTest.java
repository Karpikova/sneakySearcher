package com.example.sneakysearch.typos.wrongbutton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrongButtonInsteadOfTest {
    protected String expected = "малоко";
    @Test
    void value(){
        WrongButtonInsteadOf wb = new WrongButtonInsteadOf("молоко", 1, "а");
        String value = wb.value();
        assertEquals(expected, value);
    }
}
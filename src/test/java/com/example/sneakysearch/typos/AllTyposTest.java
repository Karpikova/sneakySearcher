package com.example.sneakysearch.typos;

import com.example.sneakysearch.typos.missedbutton.MissedInnerButtonTypos;
import com.example.sneakysearch.typos.mixedbuttons.MixedButtonsTypos;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class AllTyposTest {
    @Test
    void value() {
        String phrase = "кот";
        JointTypos allTypos = new AllTypos(List.of(
                () -> Set.of(phrase),
                new MixedButtonsTypos(phrase),
                new MissedInnerButtonTypos(phrase)));
    }
}
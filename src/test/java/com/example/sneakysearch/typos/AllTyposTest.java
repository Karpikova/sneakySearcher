package com.example.sneakysearch.typos;

import com.example.sneakysearch.MissedInnerButtonTyposTest;
import com.example.sneakysearch.MixedButtonsTyposTest;
import com.example.sneakysearch.typos.missedbutton.MissedInnerButtonTypos;
import com.example.sneakysearch.typos.mixedbuttons.MixedButtonsTypos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AllTyposTest {
    private String PHRASE = "кот";

    @Test
    void value() {
        JointTypos real = new AllTypos(List.of(
                () -> Set.of(PHRASE),
                new MixedButtonsTypos(PHRASE),
                new MissedInnerButtonTypos(PHRASE)));
        Set<String> realValue = real.value();
        Set<String> expected = expected();
        Assertions.assertTrue(realValue.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(realValue));
        Assertions.assertEquals(PHRASE, realValue.iterator().next());
    }

    Set<String> expected() {
        Set<String> expected = new LinkedHashSet<>();
        expected.add(PHRASE);
        expected.addAll(MixedButtonsTyposTest.expected);
        expected.addAll(MissedInnerButtonTyposTest.expected);
        return expected;
    }
}
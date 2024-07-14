package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.ResultLink;
import com.example.sneakysearch.result.ResultMy;
import com.example.sneakysearch.typos.AllTyposTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
public class FoundFromWebByAllTypoVariantsOfPhraseTest {
    @Test
    void foundFromWeb() throws SneakySearchException {
        LocalDate filterDate = LocalDate.of(2000, 1, 1);
        FoundFromWebByAllTypoVariantsOfPhrase ffw = new FoundFromWebByAllTypoVariantsOfPhrase(
                filterDate,
                new AllTyposTest().jointTypos(), new ResultMy(),
                FoundFromWeb.Fake::new);
        Set<ResultLink> expected = new FoundFromWeb.Fake("кот", filterDate).foundFromWeb().resultLinks();
        Set<ResultLink> real = ffw.foundFromWeb().resultLinks();

        Assertions.assertTrue(real.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(real));
    }
}
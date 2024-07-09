package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.ResultLink;
import com.example.sneakysearch.result.ResultMy;
import com.example.sneakysearch.typos.AllTyposTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
public class FoundFromWebByAllTypoVariantsOfPhraseTest {
    @Test
    void foundFromWeb() throws SneakySearchException {
        FoundFromWebByAllTypoVariantsOfPhrase ffw = new FoundFromWebByAllTypoVariantsOfPhrase(new AllTyposTest().jointTypos(),
                FoundFromWeb.Fake::new, new ResultMy());
        Set<ResultLink> expected = new FoundFromWeb.Fake("кот").foundFromWeb().resultLinks();
        Set<ResultLink> real = ffw.foundFromWeb().resultLinks();

        Assertions.assertTrue(real.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(real));
    }
}
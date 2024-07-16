package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultMy;
import com.example.sneakysearch.typos.JointTypos;

import java.time.LocalDate;
import java.util.Set;

public final class FoundFromWebByAllTypoVariantsOfPhrase implements FoundFromWeb {
    private final LocalDate filterDate;
    private final JointTypos allTypoVariantsIncludeWord;
    private final Result result;
    private final ToFoundFromWebByOnePhrase toFoundFromWebByOnePhrase;

    public FoundFromWebByAllTypoVariantsOfPhrase(LocalDate filterDate, JointTypos allTypoVariantsIncludeWord,
                                                 Result result, ToFoundFromWebByOnePhrase toFoundFromWebByOnePhrase) {
        this.filterDate = filterDate;
        this.allTypoVariantsIncludeWord = allTypoVariantsIncludeWord;
        this.result = result;
        this.toFoundFromWebByOnePhrase = toFoundFromWebByOnePhrase;
    }

    public FoundFromWebByAllTypoVariantsOfPhrase(LocalDate filterDate, JointTypos allTypoVariantsIncludeWord) {
        this(filterDate, allTypoVariantsIncludeWord, new ResultMy(), FoundFromWebByOnePhrase::new);
    }

    @Override
    public Result foundFromWeb() throws SneakySearchException {
        final Set<String> phrases = allTypoVariantsIncludeWord.value();
        for (final String phrase : phrases) {
            final FoundFromWeb foundFromWeb = toFoundFromWebByOnePhrase.foundFromWebByPhrase(phrase, filterDate);
            final Result foundFromWebResult = foundFromWeb.foundFromWeb();
            result.addLinks(foundFromWebResult.resultLinks());
        }
        return result;
    }
}

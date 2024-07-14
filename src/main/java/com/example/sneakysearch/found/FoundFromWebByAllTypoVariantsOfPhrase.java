package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultMy;
import com.example.sneakysearch.typos.AllTypos;
import com.example.sneakysearch.typos.JointTypos;
import com.example.sneakysearch.typos.doublebutton.DoubleButtonTypos;
import com.example.sneakysearch.typos.langreplacement.EnglishReplacementTypos;
import com.example.sneakysearch.typos.missedbutton.MissedInnerButtonTypos;
import com.example.sneakysearch.typos.mixedbuttons.MixedButtonsTypos;
import com.example.sneakysearch.typos.wrongbutton.AddedWrongButtonTypos;
import com.example.sneakysearch.typos.wrongbutton.Keyboard;

import java.time.LocalDate;
import java.util.List;
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

    public FoundFromWebByAllTypoVariantsOfPhrase(String phrase, LocalDate filterDate, Keyboard keyboard) {
        this(filterDate, new AllTypos(List.of(
                () -> Set.of(phrase),
                new AddedWrongButtonTypos(phrase, keyboard),
                new MixedButtonsTypos(phrase),
                new DoubleButtonTypos(phrase),
                new MissedInnerButtonTypos(phrase),
                new EnglishReplacementTypos(phrase, keyboard))));
    }

    @Override
    public Result foundFromWeb() throws SneakySearchException { //TODO test
        final Set<String> phrases = allTypoVariantsIncludeWord.value();
        for (String phrase : phrases) {
            final FoundFromWeb foundFromWeb = toFoundFromWebByOnePhrase.foundFromWebByPhrase(phrase, filterDate);
            final Result foundFromWebResult = foundFromWeb.foundFromWeb();
            result.addLinks(foundFromWebResult.resultLinks());
        }
        return result;
    }
}

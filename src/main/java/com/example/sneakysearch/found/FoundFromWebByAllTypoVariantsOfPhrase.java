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

import java.util.List;
import java.util.Set;

public final class FoundFromWebByAllTypoVariantsOfPhrase implements FoundFromWeb {
    private final JointTypos allTypoVariantsIncludeWord;
    private final Result result;
    private final ToFoundFromWebByOnePhrase toFoundFromWebByOnePhrase;

    public FoundFromWebByAllTypoVariantsOfPhrase(JointTypos allTypoVariantsIncludeWord,
                                                 ToFoundFromWebByOnePhrase toFoundFromWebByOnePhrase,
                                                 Result result) {
        this.allTypoVariantsIncludeWord = allTypoVariantsIncludeWord;
        this.toFoundFromWebByOnePhrase = toFoundFromWebByOnePhrase;
        this.result = result;
    }

    public FoundFromWebByAllTypoVariantsOfPhrase(String phrase, Keyboard keyboard) {
        this(new AllTypos(List.of(
                        () -> Set.of(phrase),
                        new AddedWrongButtonTypos(phrase, keyboard),
                        new MixedButtonsTypos(phrase),
                        new DoubleButtonTypos(phrase),
                        new MissedInnerButtonTypos(phrase),
                        new EnglishReplacementTypos(phrase, keyboard))),
                FoundFromWebByOnePhrase::new,
                new ResultMy());
    }

    @Override
    public Result foundFromWeb() throws SneakySearchException {
        final Set<String> phrases = allTypoVariantsIncludeWord.value();
        for (String phrase : phrases) {
            final FoundFromWeb foundFromWeb = toFoundFromWebByOnePhrase.foundFromWebByPhrase(phrase);
            final Result foundFromWebResult = foundFromWeb.foundFromWeb();
            result.addLinks(foundFromWebResult.resultLinks());
        }
        return result;
    }
}

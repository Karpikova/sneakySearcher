package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultMy;
import com.example.sneakysearch.typos.AddedWrongButtonTypos;
import com.example.sneakysearch.typos.AllTypos;
import com.example.sneakysearch.typos.DoubleButtonTypos;
import com.example.sneakysearch.typos.EnglishReplacementTypos;
import com.example.sneakysearch.typos.JointTypos;
import com.example.sneakysearch.typos.Keyboard;
import com.example.sneakysearch.typos.MissedInnerButtonTypos;
import com.example.sneakysearch.typos.MixedButtonsTypos;

import java.util.List;
import java.util.Set;

public final class FoundFromWebByAllTypoVariantsOfWordMy implements FoundFromWeb {
    private final JointTypos allTypoVariantsIncludeWord;
    private final ToFoundFromWebByOneWord toFoundFromWebByOneWord;
    private final Result result;

    public FoundFromWebByAllTypoVariantsOfWordMy(JointTypos allTypoVariantsIncludeWord,
                                                 ToFoundFromWebByOneWord toFoundFromWebByOneWord,
                                                 Result result) {
        this.allTypoVariantsIncludeWord = allTypoVariantsIncludeWord;
        this.toFoundFromWebByOneWord = toFoundFromWebByOneWord;
        this.result = result;
    }

    public FoundFromWebByAllTypoVariantsOfWordMy(String word, Keyboard keyboard) {
        this(new AllTypos(List.of(
                        () -> Set.of(word),
                        new AddedWrongButtonTypos(word, keyboard),
                        new MixedButtonsTypos(word),
                        new DoubleButtonTypos(word),
                        new MissedInnerButtonTypos(word),
                        new EnglishReplacementTypos(word, keyboard))),
                FoundFromWebByOneWord::new,
                new ResultMy());
    }

    @Override
    public Result foundFromWeb() throws SneakySearchException {
        final Set<String> words = allTypoVariantsIncludeWord.value();
        for (String word : words) {
            final FoundFromWeb foundFromWeb = toFoundFromWebByOneWord.foundFromWebByWord(word);
            final Result foundFromWebResult = foundFromWeb.foundFromWeb();
            result.addLinks(foundFromWebResult.resultLinks());
        }
        return result;
    }
}

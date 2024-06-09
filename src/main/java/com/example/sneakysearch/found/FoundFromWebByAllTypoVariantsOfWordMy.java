package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultMy;
import com.example.sneakysearch.typos.AddedWrongButtonTypos;
import com.example.sneakysearch.typos.AllTypos;
import com.example.sneakysearch.typos.DoubleButtonTypos;
import com.example.sneakysearch.typos.JointTypos;
import com.example.sneakysearch.typos.MissedInnerButtonTypos;
import com.example.sneakysearch.typos.MixedButtonsTypos;
import com.example.sneakysearch.typos.RussianKeyboard;

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

    public FoundFromWebByAllTypoVariantsOfWordMy(String word) {
        this(new AllTypos(List.of(
                        new AddedWrongButtonTypos(word, new RussianKeyboard()),
                        new MixedButtonsTypos(word),
                        new DoubleButtonTypos(word),
                        new MissedInnerButtonTypos(word),
                        () -> Set.of(word))),
                w -> new FoundFromWebByOneWord(w),
                new ResultMy());
    }

    @Override
    public Result foundFromWeb() throws SneakySearchException {
        Set<String> words = allTypoVariantsIncludeWord.value();
        for (String word : words) {
            String lcWord = word.toLowerCase();
            FoundFromWeb foundFromWeb = toFoundFromWebByOneWord.foundFromWebByWord(lcWord);
            Result foundFromWebResult = foundFromWeb.foundFromWeb();
            result.addLinks(foundFromWebResult.resultLinks());
        }
        return result;
    }
}

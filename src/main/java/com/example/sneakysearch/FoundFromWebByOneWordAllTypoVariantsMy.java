package com.example.sneakysearch;

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

public final class FoundFromWebByOneWordAllTypoVariantsMy implements FoundFromWebByOneWordAllTypoVariants {
    private final JointTypos allTypoVariantsIncludeWord;
    private final ToFoundFromWebByWord toFoundFromWebByWord;
    private Result result;

    public FoundFromWebByOneWordAllTypoVariantsMy(JointTypos allTypoVariantsIncludeWord,
                                                  ToFoundFromWebByWord toFoundFromWebByWord,
                                                  Result result) {
        this.allTypoVariantsIncludeWord = allTypoVariantsIncludeWord;
        this.toFoundFromWebByWord = toFoundFromWebByWord;
        this.result = result;
    }

    public FoundFromWebByOneWordAllTypoVariantsMy(String word) {
        this(new AllTypos(List.of(
                        new AddedWrongButtonTypos(word, new RussianKeyboard()),
                        new MixedButtonsTypos(word),
                        new DoubleButtonTypos(word),
                        new MissedInnerButtonTypos(word),
                        () -> Set.of(word))),
                w -> new FoundFromWebByOneWordVariantMy(w),
                new ResultMy());
    }

    @Override
    public Result foundFromWeb() {
        Set<String> words = allTypoVariantsIncludeWord.value();
        for (String word : words) {
            FoundFromWebByWord foundFromWebByWord = toFoundFromWebByWord.foundFromWebByWord(word);
            Result foundFromWebResult = foundFromWebByWord.foundFromWeb();
            result.addLinks(foundFromWebResult.resultLinks());
            result.addMistakes(foundFromWebResult.mistakes());
        }
        return result;
    }
}

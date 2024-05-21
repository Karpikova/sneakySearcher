package com.example.sneakysearch;

import com.example.sneakysearch.typos.AddedWrongButtonTypos;
import com.example.sneakysearch.typos.AllTypos;
import com.example.sneakysearch.typos.DoubleButtonTypos;
import com.example.sneakysearch.typos.JointTypos;
import com.example.sneakysearch.typos.MissedInnerButtonTypos;
import com.example.sneakysearch.typos.MixedButtonsTypos;
import com.example.sneakysearch.typos.RussianKeyboard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class FoundFromWebByOneWordAllTypoVariantsMy implements FoundFromWebByOneWordAllTypoVariants {
    private final JointTypos allTypoVariantsIncludeWord;
    private final ToFoundFromWebByWord toFoundFromWebByWord;

    public FoundFromWebByOneWordAllTypoVariantsMy(JointTypos allTypoVariantsIncludeWord, ToFoundFromWebByWord toFoundFromWebByWord) {
        this.allTypoVariantsIncludeWord = allTypoVariantsIncludeWord;
        this.toFoundFromWebByWord = toFoundFromWebByWord;
    }

    public FoundFromWebByOneWordAllTypoVariantsMy(String word) {
        this(new AllTypos(List.of(
                        new AddedWrongButtonTypos(word, new RussianKeyboard()),
                        new MixedButtonsTypos(word),
                        new DoubleButtonTypos(word),
                        new MissedInnerButtonTypos(word),
                        () -> Set.of(word))),
                w -> new FoundFromWebByOneWordVariantMy(w));
    }

    @Override
    public Set<ResultLink> foundFromWeb() {
        Set<ResultLink> resultLinks = new HashSet<>();
        Set<String> words = allTypoVariantsIncludeWord.value();
        for (String word : words) {
            FoundFromWebByWord foundFromWebByWord = toFoundFromWebByWord.foundFromWebByWord(word);
            resultLinks.addAll(foundFromWebByWord.foundFromWeb());
        }
        return resultLinks;
    }
}

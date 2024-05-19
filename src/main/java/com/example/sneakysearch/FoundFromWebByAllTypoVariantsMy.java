package com.example.sneakysearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class FoundFromWebByAllTypoVariantsMy implements FoundFromWebByAllTypoVariants {
    private final JointTypos allWordVariants;
    private final ToFoundFromWebByWord toFoundFromWebByWord;

    public FoundFromWebByAllTypoVariantsMy(JointTypos allWordVariants, ToFoundFromWebByWord toFoundFromWebByWord) {
        this.allWordVariants = allWordVariants;
        this.toFoundFromWebByWord = toFoundFromWebByWord;
    }

    public FoundFromWebByAllTypoVariantsMy(String word) {
        this(new AllRussianTypoVariants(List.of(
                        new TyposWithWrongButton(word, new RussianKeyboard()),
                        new TyposWithoutWrongButton(word))),
                w -> new FoundFromWebByOneWordFormMy(w));
    }

    @Override
    public Set<ResultLink> foundFromWeb() {//ад
        Set<ResultLink> resultLinks = new HashSet<>();
        Set<String> words = allWordVariants.value();
        for (String word : words) {
            FoundFromWebByWord foundFromWebByWord = toFoundFromWebByWord.foundFromWebByWord(word);
            resultLinks.addAll(foundFromWebByWord.foundFromWeb());
        }
        return resultLinks;
    }
}

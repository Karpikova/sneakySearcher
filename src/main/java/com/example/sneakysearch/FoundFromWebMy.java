package com.example.sneakysearch;

import java.util.List;
import java.util.Set;

public class FoundFromWebMy implements FoundFromWeb {
    private AllRussianTypoVariants allWordVariants;

    public FoundFromWebMy(AllRussianTypoVariants allWordVariants) {
        this.allWordVariants = allWordVariants;
    }

    public FoundFromWebMy(String word) {
        this(new AllRussianTypoVariants(List.of(
                new TyposWithWrongButtonMy(word, new RussianKeyboard()),
                new TyposWithoutWrongButtonMy(word))));
    }

    @Override
    public Set<String> foundFromWeb() {//поменять на сет линков
        Set<String> value = allWordVariants.value();
        return value;
    }
}

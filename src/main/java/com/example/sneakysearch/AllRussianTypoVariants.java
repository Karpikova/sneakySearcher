package com.example.sneakysearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllRussianTypoVariants implements JointTypos {
    private List<Typos> typos;

    public AllRussianTypoVariants(List<Typos> typos) {
        this.typos = typos;
    }

    public Set<String> value() {
        Set<String> allTyposTogether = new HashSet<>();
        for (Typos typo : typos) {
            allTyposTogether.addAll(typo.value());
        }
        return allTyposTogether;
    }
}
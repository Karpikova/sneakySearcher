package com.example.sneakysearch.typos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AllTypos implements JointTypos {
    private final List<Typos> typos;

    public AllTypos(List<Typos> typos) {
        this.typos = typos;
    }

    public Set<String> value() {
        final Set<String> allTyposTogether = new HashSet<>();
        for (Typos typo : typos) {
            allTyposTogether.addAll(typo.value());
        }
        return allTyposTogether;
    }
}
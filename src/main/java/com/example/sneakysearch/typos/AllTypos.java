package com.example.sneakysearch.typos;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class AllTypos implements JointTypos {
    private final List<Typos> typos;
    public AllTypos(List<Typos> typos) {
        this.typos = typos;
    }

    public Set<String> value() {
        return typos.stream().flatMap(t -> t.value().stream()).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
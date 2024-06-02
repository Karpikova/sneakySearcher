package com.example.sneakysearch.result;

import java.util.List;
import java.util.Set;

public interface Result {
    Set<ResultLink> resultLinks();

    List<Mistake> mistakes();

    void addLinks(Set<ResultLink> links);

    void addMistakes(List<Mistake> mistakes);
}

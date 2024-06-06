package com.example.sneakysearch.result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResultMy implements Result {
    private Set<ResultLink> resultLinks;

    private List<Mistake> mistakes;

    public ResultMy() {
        resultLinks = new HashSet<>();
        mistakes = new ArrayList<>();
    }

    @Override
    public Set<ResultLink> resultLinks() {
        return resultLinks;
    }

    @Override
    public List<Mistake> mistakes() {
        return mistakes;
    }

    @Override
    public void addLinks(Set<ResultLink> links) {
        this.resultLinks.addAll(links);
    }

    @Override
    public void addLink(ResultLink link) {
        this.resultLinks.add(link);
    }

    @Override
    public void addMistakes(List<Mistake> ms) {
        this.mistakes.addAll(ms);
    }

    @Override
    public void addMistake(Mistake mistake) {
        this.mistakes.add(mistake);
    }

}

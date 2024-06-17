package com.example.sneakysearch.result;

import java.util.LinkedHashSet;
import java.util.Set;

public final class ResultMy implements Result {
    private final Set<ResultLink> resultLinks;

    public ResultMy() {
        resultLinks = new LinkedHashSet<>(); //Чтобы исходное слово было первым
   }

    @Override
    public Set<ResultLink> resultLinks() {
        return resultLinks;
    }

    @Override
    public void addLinks(Set<ResultLink> links) {
        this.resultLinks.addAll(links);
    }

    @Override
    public void addLink(ResultLink link) {
        this.resultLinks.add(link);
    }

}

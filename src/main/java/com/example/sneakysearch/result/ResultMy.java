package com.example.sneakysearch.result;

import java.util.Set;
import java.util.TreeSet;

public final class ResultMy implements Result {
    private final Set<ResultLink> resultLinks;

    public ResultMy(Set<ResultLink> resultLinks) {
        this.resultLinks = resultLinks;
    }

    public ResultMy() {
        this(new TreeSet<>());
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

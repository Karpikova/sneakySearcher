package com.example.sneakysearch.result;

import java.util.HashSet;
import java.util.Set;

public class ResultMy implements Result {
    private Set<ResultLink> resultLinks;

    public ResultMy() {
        resultLinks = new HashSet<>();
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

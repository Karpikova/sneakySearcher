package com.example.sneakysearch.result;

import java.util.Set;

public interface Result {
    Set<ResultLink> resultLinks();
    void addLinks(Set<ResultLink> links);
    void addLink(ResultLink link);
}

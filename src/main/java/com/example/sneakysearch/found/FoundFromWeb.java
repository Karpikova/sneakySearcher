package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.Result;

public interface FoundFromWeb {
    Result foundFromWeb() throws SneakySearchException;
}

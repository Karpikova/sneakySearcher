package com.example.sneakysearch.found;

import java.time.LocalDate;

@FunctionalInterface
public interface
ToFoundFromWebByOnePhrase {
    FoundFromWeb foundFromWebByPhrase(String phrase, LocalDate filterDate);
}

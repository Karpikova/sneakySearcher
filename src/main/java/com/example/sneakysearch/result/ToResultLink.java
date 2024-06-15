package com.example.sneakysearch.result;

@FunctionalInterface
public interface ToResultLink {
    ResultLink resultLink(String w, String name, String number, String customer, String link, int ordinal);
}

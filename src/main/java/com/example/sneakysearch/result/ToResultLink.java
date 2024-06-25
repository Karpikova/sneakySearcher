package com.example.sneakysearch.result;

@FunctionalInterface
public interface ToResultLink {
    ResultLink resultLink(String phrase, String name, String number, String customer, String link);
}

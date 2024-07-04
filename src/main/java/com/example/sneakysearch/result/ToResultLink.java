package com.example.sneakysearch.result;

import java.time.LocalDate;

@FunctionalInterface
public interface ToResultLink {
    ResultLink resultLink(String phrase, String name, String number, String customer, String link, LocalDate date);
}

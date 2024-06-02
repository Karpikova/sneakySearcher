package com.example.sneakysearch;

import com.example.sneakysearch.result.ResultLink;

public interface ToResultLink {
    ResultLink resultLink(String w, String name, String number, String customer, String link, int ordinal);
}

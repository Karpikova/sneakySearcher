package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultLink;
import com.example.sneakysearch.result.ResultLinkWithPurchaseObject;
import com.example.sneakysearch.result.ResultMy;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface FoundFromWeb {
    Result foundFromWeb() throws SneakySearchException;

    final class Fake implements FoundFromWeb {

        public Fake(String word, LocalDate filterDate) {
        }

        @Override
        public Result foundFromWeb() throws SneakySearchException {
            ResultLinkWithPurchaseObject linkWithPOCat = new ResultLinkWithPurchaseObject("кот", "Трамвай", "42",
                    "Панаев и Скабический", "https://karpikova.ru",
                    LocalDate.of(1891, 5, 15));
            ResultLinkWithPurchaseObject linkWithPOCt = new ResultLinkWithPurchaseObject("кт", "Метла", "42",
                    "Панаев и Скабический", "https://javateacher.ru",
                    LocalDate.of(1940, 3, 10));
            Set<ResultLink> resultLinks = new TreeSet<>(List.of(linkWithPOCat, linkWithPOCt));
            return new ResultMy(resultLinks);
        }
    }
}

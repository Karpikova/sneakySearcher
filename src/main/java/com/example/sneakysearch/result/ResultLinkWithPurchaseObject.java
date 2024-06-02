package com.example.sneakysearch.result;

import java.util.Objects;

public class ResultLinkWithPurchaseObject implements ResultLink {
    private final PurchaseObject purchaseObject;
    private final String searchedWord;
    private final String link;
    private final int ordinal;

    public ResultLinkWithPurchaseObject(PurchaseObject purchaseObject, String searchedWord, String link, int ordinal) {
        this.purchaseObject = purchaseObject;
        this.searchedWord = searchedWord;
        this.link = link;
        this.ordinal = ordinal;
    }

    public ResultLinkWithPurchaseObject(String word, String purchaseObject, String number, String customer, String link, int ordinal) {
        this(new PurchaseObjectMy(purchaseObject, number, customer), word, link, ordinal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultLinkWithPurchaseObject that = (ResultLinkWithPurchaseObject) o;
        return Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public PurchaseObject purchaseObject() {
        return purchaseObject;
    }

    @Override
    public String searchedWord() {
        return searchedWord;
    }

    @Override
    public String link() {
        return link;
    }

    @Override
    public int ordinal() {
        return ordinal;
    }
}

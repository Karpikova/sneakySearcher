package com.example.sneakysearch;

import java.util.Objects;

public class ResultPurchaseObject implements ResultLink {
    private String searchedWord;
    private String purchaseObject;
    private String number;
    private String customer;
    private String link;

    public ResultPurchaseObject(String searchedWord, String purchaseObject, String number, String customer, String link) {
        this.searchedWord = searchedWord;
        this.purchaseObject = purchaseObject;
        this.number = number;
        this.customer = customer;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultPurchaseObject that = (ResultPurchaseObject) o;
        return Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public String searchedWord() {
        return searchedWord;
    }

    public String purchaseObject() {
        return purchaseObject;
    }

    public String number() {
        return number;
    }

    public String customer() {
        return customer;
    }

    public String link() {
        return link;
    }
}

package com.example.sneakysearch.result;

import java.time.LocalDate;
import java.util.Objects;

public final class ResultLinkWithPurchaseObject implements ResultLink {
    private final PurchaseObject purchaseObject;
    private final String phrase;
    private final String link;

    public ResultLinkWithPurchaseObject(PurchaseObject purchaseObject, String phrase, String link) {
        this.purchaseObject = purchaseObject;
        this.phrase = phrase;
        this.link = link;
    }

    public ResultLinkWithPurchaseObject
            (String phrase, String purchaseObject, String number, String customer, String link, LocalDate date) {
        this(new PurchaseObjectMy(purchaseObject, number, customer, date), phrase, link);
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
    public String searchedPhrase() {
        return phrase;
    }

    @Override
    public String link() {
        return link;
    }

}

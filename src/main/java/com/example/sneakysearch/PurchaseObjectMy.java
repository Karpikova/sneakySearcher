package com.example.sneakysearch;

public class PurchaseObjectMy implements PurchaseObject {
    String name;
    String number;
    String customer;

    public PurchaseObjectMy(String name, String number, String customer) {
        this.name = name;
        this.number = number;
        this.customer = customer;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String number() {
        return number;
    }

    @Override
    public String customer() {
        return customer;
    }
}
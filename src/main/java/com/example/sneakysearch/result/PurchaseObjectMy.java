package com.example.sneakysearch.result;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class PurchaseObjectMy implements PurchaseObject {
    private final String name;
    private final String number;
    private final String customer;
    private final LocalDate date;
    private final static String DATE_PATTERN = "dd.MM.yyyy";

    public PurchaseObjectMy(String name, String number, String customer, LocalDate date) {
        this.name = name;
        this.number = number;
        this.customer = customer;
        this.date = date;
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

    @Override
    public LocalDate date() {
        return date;
    }

    @Override
    public String formattedDate() {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
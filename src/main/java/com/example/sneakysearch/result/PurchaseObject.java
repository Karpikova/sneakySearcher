package com.example.sneakysearch.result;

import java.time.LocalDate;

public interface PurchaseObject {
    String name();
    String number();
    String customer();
    LocalDate date();
    String formattedDate();

}

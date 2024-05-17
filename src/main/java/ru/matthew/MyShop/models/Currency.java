package ru.matthew.MyShop.models;

public enum Currency {

    BYN("BYN"), USD("USD"), EUR("EUR");

    private final String curCode;

    Currency(String cur) {
        curCode = cur;
    }
}

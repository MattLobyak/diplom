package ru.matthew.MyShop.models;

public enum OrderPayment {

    CASH("Наличными"), CARD("Картой");

    OrderPayment(String rus) {
        ruValue = rus;
    }

    private final String ruValue;

    public String getRuValue() {
        return ruValue;
    }
}

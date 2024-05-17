package ru.matthew.MyShop.models;

public enum Payment {

    PRICE("Цена"), NEGOTIATED("Договорная цена"), FREE("Бесплатно");

    Payment(String rus) {
        ruValue = rus;
    }

    private final String ruValue;

    public String getRuValue() {
        return ruValue;
    }
}

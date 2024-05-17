package ru.matthew.MyShop.models;

public enum Category {

    MINSK("Минск"),
    GOMEL("Гомель"),
    GRODNO("Гродно"),
    BREST("Брест"),
    VITEBSK("Витебск"),
    MOGILEV("Могилёв");

    Category(String rus) {
        ruValue = rus;
    }

    private final String ruValue;

    public String getRuValue() {
        return ruValue;
    }
}
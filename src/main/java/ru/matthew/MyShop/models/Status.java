package ru.matthew.MyShop.models;

public enum Status {

    MODERATION("В модерации"), ACTIVE("Активно"), BLOCKED("Заблокировано");

    Status(String rus) {
        ruValue = rus;
    }

    private final String ruValue;

    public String getRuValue() {
        return ruValue;
    }
}

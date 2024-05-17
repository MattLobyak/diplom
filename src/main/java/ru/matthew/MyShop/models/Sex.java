package ru.matthew.MyShop.models;

public enum Sex {
    MALE("Мужчина"), FEMALE("Женщина"), ATTACK_HELICOPTER("Не выбирать");
    Sex(String rus) {
        ruValue = rus;
    }

    private final String ruValue;

    public String getRuValue() {
        return ruValue;
    }
}

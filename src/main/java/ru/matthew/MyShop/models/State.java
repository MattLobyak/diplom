package ru.matthew.MyShop.models;

public enum State {
    NEW("Новое"),
    PASTINUSAGE("Б/у");



    State(String rus) {
        ruValue = rus;
    }

    private final String ruValue;

    public String getRuValue() {
        return ruValue;
    }
}

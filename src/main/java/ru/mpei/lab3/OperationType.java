package ru.mpei.lab3;

public enum OperationType {
    ADD("add"),
    DELETE("delete"),
    GET("get");

    private final String title;

    OperationType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

package ru.mpei.lab3;

public enum ListType {
    ARRAY_LIST("ArrayList"),
    LINKED_LIST("LinkedList");

    private final String title;

    ListType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

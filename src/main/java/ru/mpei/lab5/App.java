package ru.mpei.lab5;

public class App {
    public static void main(String[] args) {
        SomeBean bean = new Injector().inject(new SomeBean());
        bean.foo();
    }
}

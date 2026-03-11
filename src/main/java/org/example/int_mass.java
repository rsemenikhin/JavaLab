package org.example;

/**
 * Контейнер для хранения целых чисел.
 * Реализован на основе динамического массива.
 */
public class IntContainer {
    private static final int DEFAULT_CAPACITY = 10;

    private int[] elements;
    private int size;

    /**
     * Создает контейнер с начальной вместимостью по умолчанию.
     */
    public IntContainer() {
        this.elements = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Создает контейнер с указанной начальной вместимостью.
     *
     * @param initialCapacity начальная вместимость контейнера
     * @throws IllegalArgumentException если initialCapacity меньше 0
     */
    public IntContainer(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative");
        }

        this.elements = new int[initialCapacity];
        this.size = 0;
    }

    /**
     * Возвращает текущее количество элементов в контейнере.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли контейнер.
     *
     * @return true, если контейнер пуст, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
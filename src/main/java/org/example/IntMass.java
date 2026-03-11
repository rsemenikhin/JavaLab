package org.example;

/**
 * Контейнер для хранения целых чисел.
 * Реализован на основе динамического массива.
 */
public class IntMass {
    private static final int DEFAULT_CAPACITY = 10;

    private int[] elements;
    private int size;

    /**
     * Создает контейнер с начальной вместимостью по умолчанию.
     */
    public IntMass() {
        this.elements = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Создает контейнер с указанной начальной вместимостью.
     *
     * @param initialCapacity начальная вместимость контейнера
     * @throws IllegalArgumentException если initialCapacity меньше 0
     */
    public IntMass(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative");
        }

        this.elements = new int[initialCapacity];
        this.size = 0;
    }

    /**
     * Добавляет элемент в контейнер.
     *
     * @param value значение для добавления
     */
    public void add(int value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    /**
     * Увеличивает вместимость внутреннего массива, если он заполнен.
     */
    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }

        int newCapacity = elements.length == 0 ? 1 : elements.length * 2;
        int[] newElements = new int[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Проверяет корректность индекса.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс некорректен
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public int removeAt(int index) {
        checkIndex(index);

        int removedValue = elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        return removedValue;
    }

    /**
     * Возвращает строковое представление контейнера.
     *
     * @return строка с элементами контейнера
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (int i = 0; i < size; i++) {
            builder.append(elements[i]);

            if (i < size - 1) {
                builder.append(", ");
            }
        }

        builder.append("]");
        return builder.toString();
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
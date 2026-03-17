package org.example;

/**
 * Исключение, выбрасываемое при ошибках разбора или вычисления выражения.
 */
public class InvalidExpressionException extends RuntimeException {

    /**
     * Создает исключение с описанием ошибки.
     *
     * @param message текст ошибки
     */
    public InvalidExpressionException(String message) {
        super(message);
    }
}
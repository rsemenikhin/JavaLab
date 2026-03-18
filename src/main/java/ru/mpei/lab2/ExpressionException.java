package ru.mpei.lab2;

/**
 * Сообщает о проблеме в записи выражения или во время его вычисления.
 */
public class ExpressionException extends RuntimeException {

    /**
     * Создает исключение с пояснением ошибки.
     *
     * @param message текст ошибки
     */
    public ExpressionException(String message) {
        super(message);
    }
}
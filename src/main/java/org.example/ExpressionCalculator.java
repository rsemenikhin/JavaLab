package org.example;

/**
 * Вычисляет арифметические выражения с числами, операциями и скобками.
 */
public class ExpressionCalculator {
    private String expression;
    private int position;

    /**
     * Вычисляет значение арифметического выражения.
     *
     * @param expression строка выражения
     * @return вычисленное значение
     * @throws InvalidExpressionException если выражение некорректно
     */
    public double evaluate(String expression) {
        if (expression == null) {
            throw new InvalidExpressionException("Expression cannot be null");
        }

        this.expression = expression;
        this.position = 0;

        double result = parseExpression();
        skipWhitespace();

        if (!isEnd()) {
            throw new InvalidExpressionException("Unexpected token at position " + position);
        }

        return result;
    }

    /**
     * Разбирает сложение и вычитание.
     *
     * @return значение выражения
     */
    private double parseExpression() {
        double value = parseTerm();

        while (true) {
            skipWhitespace();

            if (match('+')) {
                value += parseTerm();
            } else if (match('-')) {
                value -= parseTerm();
            } else {
                return value;
            }
        }
    }

    /**
     * Разбирает умножение и деление.
     *
     * @return значение терма
     */
    private double parseTerm() {
        double value = parseFactor();

        while (true) {
            skipWhitespace();

            if (match('*')) {
                value *= parseFactor();
            } else if (match('/')) {
                double divisor = parseFactor();

                if (divisor == 0.0) {
                    throw new InvalidExpressionException("Division by zero");
                }

                value /= divisor;
            } else {
                return value;
            }
        }
    }

    /**
     * Разбирает число, скобочное выражение или унарный знак.
     *
     * @return значение множителя
     */
    private double parseFactor() {
        skipWhitespace();

        if (match('+')) {
            return parseFactor();
        }

        if (match('-')) {
            return -parseFactor();
        }

        if (match('(')) {
            double value = parseExpression();
            skipWhitespace();

            if (!match(')')) {
                throw new InvalidExpressionException("Missing closing parenthesis at position " + position);
            }

            return value;
        }

        return parseNumber();
    }

    /**
     * Разбирает число с плавающей точкой.
     *
     * @return считанное число
     */
    private double parseNumber() {
        skipWhitespace();
        int start = position;
        boolean hasDigit = false;

        while (!isEnd() && Character.isDigit(currentChar())) {
            position++;
            hasDigit = true;
        }

        if (!isEnd() && currentChar() == '.') {
            position++;

            while (!isEnd() && Character.isDigit(currentChar())) {
                position++;
                hasDigit = true;
            }
        }

        if (!hasDigit) {
            throw new InvalidExpressionException("Number expected at position " + position);
        }

        String numberText = expression.substring(start, position);
        return Double.parseDouble(numberText);
    }

    /**
     * Пропускает пробельные символы.
     */
    private void skipWhitespace() {
        while (!isEnd() && Character.isWhitespace(currentChar())) {
            position++;
        }
    }

    /**
     * Проверяет и потребляет ожидаемый символ.
     *
     * @param expected ожидаемый символ
     * @return true, если символ совпал
     */
    private boolean match(char expected) {
        if (!isEnd() && currentChar() == expected) {
            position++;
            return true;
        }

        return false;
    }


    /**
     * Возвращает текущий символ.
     *
     * @return текущий символ
     */
    private char currentChar() {
        return expression.charAt(position);
    }

    /**
     * Проверяет достижение конца строки.
     *
     * @return true, если строка закончилась
     */
    private boolean isEnd() {
        return position >= expression.length();
    }
}
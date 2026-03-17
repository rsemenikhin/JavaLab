package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionCalculatorTest {

    @Test
    void evaluateShouldRespectOperatorPrecedence() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result = calculator.evaluate("2 + 3 * 4");

        assertEquals(14.0, result);
    }

    @Test
    void evaluateShouldHandleParentheses() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result = calculator.evaluate("(2 + 3) * 4");

        assertEquals(20.0, result);
    }

    @Test
    void evaluateShouldHandleUnaryMinus() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result = calculator.evaluate("-2 + 5");

        assertEquals(3.0, result);
    }

    @Test
    void evaluateShouldHandleFractionalNumbers() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        double result = calculator.evaluate("3.5 + 1.5");

        assertEquals(5.0, result);
    }

    @Test
    void evaluateShouldThrowExceptionForMissingParenthesis() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        assertThrows(InvalidExpressionException.class, () -> calculator.evaluate("(2 + 3"));
    }

    @Test
    void evaluateShouldThrowExceptionForUnexpectedToken() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        assertThrows(InvalidExpressionException.class, () -> calculator.evaluate("2 + * 3"));
    }

    @Test
    void evaluateShouldThrowExceptionForDivisionByZero() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        assertThrows(InvalidExpressionException.class, () -> calculator.evaluate("10 / (5 - 5)"));
    }
}
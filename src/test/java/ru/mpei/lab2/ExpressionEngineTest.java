package ru.mpei.lab2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionEngineTest {
    private static final double EPS = 1e-9;

    @Test
    void shouldRespectPriorityOfOperations() {
        ExpressionEngine engine = new ExpressionEngine();

        double value = engine.evaluate("2 + 3 * 4");

        assertEquals(14.0, value, EPS);
    }

    @Test
    void shouldSupportParentheses() {
        ExpressionEngine engine = new ExpressionEngine();

        double value = engine.evaluate("(2 + 3) * 4");

        assertEquals(20.0, value, EPS);
    }

    @Test
    void shouldSupportUnaryOperations() {
        ExpressionEngine engine = new ExpressionEngine();

        double value = engine.evaluate("-2 + +5");

        assertEquals(3.0, value, EPS);
    }

    @Test
    void shouldSupportFractionalValues() {
        ExpressionEngine engine = new ExpressionEngine();

        double value = engine.evaluate("7.25 - 2.25");

        assertEquals(5.0, value, EPS);
    }

    @Test
    void shouldSupportRightAssociativePower() {
        ExpressionEngine engine = new ExpressionEngine();

        double value = engine.evaluate("2 ^ 3 ^ 2");

        assertEquals(512.0, value, EPS);
    }

    @Test
    void shouldUseVariablesAndFunctionsTogether() {
        ExpressionEngine engine = new ExpressionEngine();
        Map<String, Double> variables = new HashMap<String, Double>();
        variables.put("a", 9.0);
        variables.put("b", 3.0);

        double value = engine.evaluate("sqrt(a) + max(b, 2, 1)", variables);

        assertEquals(6.0, value, EPS);
    }

    @Test
    void shouldCollectUniqueVariablesInEncounterOrder() {
        ExpressionEngine engine = new ExpressionEngine();

        Set<String> variables = engine.collectVariables("rate * years + max(rate, bonus) - log(total)");

        assertEquals(new LinkedHashSet<String>(Arrays.asList("rate", "years", "bonus", "total")), variables);
    }

    @Test
    void shouldAskForRepeatedVariableOnlyOnceDuringScan() {
        ExpressionEngine engine = new ExpressionEngine();

        Set<String> variables = engine.collectVariables("x + x * max(x, y)");

        assertEquals(new LinkedHashSet<String>(Arrays.asList("x", "y")), variables);
    }

    @Test
    void shouldWorkWithConstants() {
        ExpressionEngine engine = new ExpressionEngine();

        double value = engine.evaluate("sin(pi / 2) + abs(-3)");

        assertEquals(4.0, value, EPS);
    }

    @Test
    void shouldFailOnMissingBracket() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("(1 + 2"));
    }

    @Test
    void shouldFailOnDivisionByZero() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("5 / (3 - 3)"));
    }

    @Test
    void shouldFailOnUnknownVariable() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("x + 2", Collections.<String, Double>emptyMap()));
    }

    @Test
    void shouldFailOnUnknownFunction() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("foo(10)"));
    }

    @Test
    void shouldFailOnWrongFunctionArity() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("pow(2)"));
    }

    @Test
    void shouldFailOnNegativeSqrtArgument() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("sqrt(-1)"));
    }

    @Test
    void shouldFailOnNonPositiveLogarithmArgument() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("log(0)"));
    }

    @Test
    void shouldFailOnUnexpectedToken() {
        ExpressionEngine engine = new ExpressionEngine();

        assertThrows(ExpressionException.class, () -> engine.evaluate("2 + * 7"));
    }
}
package ru.mpei.lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Разбирает и вычисляет арифметические выражения.
 */
public class ExpressionEngine {
    private static final Map<String, Double> CONSTANTS = createConstants();
    private static final Map<String, BuiltInFunction> FUNCTIONS = createFunctions();

    /**
     * Вычисляет выражение без пользовательских переменных.
     *
     * @param expression исходное выражение
     * @return вычисленное значение
     */
    public double evaluate(String expression) {
        return evaluate(expression, Collections.<String, Double>emptyMap());
    }

    /**
     * Вычисляет выражение с заданными значениями переменных.
     *
     * @param expression исходное выражение
     * @param variables значения переменных
     * @return вычисленное значение
     */
    public double evaluate(String expression, Map<String, Double> variables) {
        ExpressionParser parser = new ExpressionParser(expression, variables, false);
        return parser.parseWholeExpression();
    }

    /**
     * Находит переменные в выражении в порядке их первого появления.
     *
     * @param expression исходное выражение
     * @return множество имен переменных
     */
    public Set<String> collectVariables(String expression) {
        ExpressionParser parser = new ExpressionParser(expression, Collections.<String, Double>emptyMap(), true);
        parser.parseWholeExpression();
        return parser.getVariableNames();
    }

    private static Map<String, Double> createConstants() {
        Map<String, Double> constants = new HashMap<String, Double>();
        constants.put("pi", Math.PI);
        constants.put("e", Math.E);
        return Collections.unmodifiableMap(constants);
    }

    private static Map<String, BuiltInFunction> createFunctions() {
        Map<String, BuiltInFunction> functions = new HashMap<String, BuiltInFunction>();
        functions.put("sin", new BuiltInFunction(1, 1) {
            @Override
            double call(List<Double> arguments) {
                return Math.sin(arguments.get(0));
            }
        });
        functions.put("cos", new BuiltInFunction(1, 1) {
            @Override
            double call(List<Double> arguments) {
                return Math.cos(arguments.get(0));
            }
        });
        functions.put("abs", new BuiltInFunction(1, 1) {
            @Override
            double call(List<Double> arguments) {
                return Math.abs(arguments.get(0));
            }
        });
        functions.put("sqrt", new BuiltInFunction(1, 1) {
            @Override
            double call(List<Double> arguments) {
                double value = arguments.get(0);
                if (value < 0.0d) {
                    throw new ExpressionException("Нельзя извлечь корень из отрицательного числа.");
                }
                return Math.sqrt(value);
            }
        });
        functions.put("log", new BuiltInFunction(1, 1) {
            @Override
            double call(List<Double> arguments) {
                double value = arguments.get(0);
                if (value <= 0.0d) {
                    throw new ExpressionException("Логарифм определен только для положительных чисел.");
                }
                return Math.log(value);
            }
        });
        functions.put("pow", new BuiltInFunction(2, 2) {
            @Override
            double call(List<Double> arguments) {
                return Math.pow(arguments.get(0), arguments.get(1));
            }
        });
        functions.put("min", new BuiltInFunction(2, Integer.MAX_VALUE) {
            @Override
            double call(List<Double> arguments) {
                double current = arguments.get(0);
                for (int index = 1; index < arguments.size(); index++) {
                    current = Math.min(current, arguments.get(index));
                }
                return current;
            }
        });
        functions.put("max", new BuiltInFunction(2, Integer.MAX_VALUE) {
            @Override
            double call(List<Double> arguments) {
                double current = arguments.get(0);
                for (int index = 1; index < arguments.size(); index++) {
                    current = Math.max(current, arguments.get(index));
                }
                return current;
            }
        });
        return Collections.unmodifiableMap(functions);
    }

    private abstract static class BuiltInFunction {
        private final int minArguments;
        private final int maxArguments;

        private BuiltInFunction(int minArguments, int maxArguments) {
            this.minArguments = minArguments;
            this.maxArguments = maxArguments;
        }

        private void checkArity(String name, int count) {
            if (count < minArguments || count > maxArguments) {
                if (minArguments == maxArguments) {
                    throw new ExpressionException("Функция " + name + " ожидает " + minArguments + " аргумент(а).");
                }
                throw new ExpressionException(
                        "Функция " + name + " ожидает от " + minArguments + " до " + maxArguments + " аргументов."
                );
            }
        }

        abstract double call(List<Double> arguments);
    }

    private static final class ExpressionParser {
        private final String source;
        private final Map<String, Double> variables;
        private final boolean scanOnly;
        private final LinkedHashSet<String> variableNames = new LinkedHashSet<String>();
        private int index;

        private ExpressionParser(String source, Map<String, Double> variables, boolean scanOnly) {
            if (source == null) {
                throw new ExpressionException("Выражение не должно быть null.");
            }
            this.source = source;
            this.variables = variables == null ? Collections.<String, Double>emptyMap() : variables;
            this.scanOnly = scanOnly;
        }

        private double parseWholeExpression() {
            double value = parseSum();
            skipSpaces();
            if (!isFinished()) {
                throw new ExpressionException("Лишний символ на позиции " + index + ".");
            }
            return value;
        }

        private Set<String> getVariableNames() {
            return Collections.unmodifiableSet(variableNames);
        }

        private double parseSum() {
            double left = parseProduct();
            while (true) {
                skipSpaces();
                if (take('+')) {
                    left += parseProduct();
                } else if (take('-')) {
                    left -= parseProduct();
                } else {
                    return left;
                }
            }
        }

        private double parseProduct() {
            double left = parsePower();
            while (true) {
                skipSpaces();
                if (take('*')) {
                    left *= parsePower();
                } else if (take('/')) {
                    double right = parsePower();
                    if (right == 0.0d) {
                        throw new ExpressionException("Деление на ноль запрещено.");
                    }
                    left /= right;
                } else {
                    return left;
                }
            }
        }

        private double parsePower() {
            double base = parseUnary();
            skipSpaces();
            if (take('^')) {
                return Math.pow(base, parsePower());
            }
            return base;
        }

        private double parseUnary() {
            skipSpaces();
            if (take('+')) {
                return parseUnary();
            }
            if (take('-')) {
                return -parseUnary();
            }
            return parseAtom();
        }

        private double parseAtom() {
            skipSpaces();
            if (take('(')) {
                double nested = parseSum();
                skipSpaces();
                if (!take(')')) {
                    throw new ExpressionException("Не найдена закрывающая скобка.");
                }
                return nested;
            }
            if (!isFinished() && (Character.isLetter(currentChar()) || currentChar() == '_')) {
                return parseNameOrFunction();
            }
            return parseNumber();
        }

        private double parseNameOrFunction() {
            String token = parseIdentifier();
            String lowered = token.toLowerCase(Locale.ROOT);

            skipSpaces();
            if (take('(')) {
                return parseFunction(lowered);
            }

            if (CONSTANTS.containsKey(lowered)) {
                return CONSTANTS.get(lowered);
            }

            variableNames.add(token);
            if (variables.containsKey(token)) {
                return variables.get(token);
            }
            if (scanOnly) {
                return 0.0d;
            }
            throw new ExpressionException("Для переменной " + token + " не задано значение.");
        }

        private double parseFunction(String name) {
            BuiltInFunction function = FUNCTIONS.get(name);
            if (function == null) {
                throw new ExpressionException("Неизвестная функция: " + name + ".");
            }

            List<Double> arguments = new ArrayList<Double>();
            skipSpaces();
            if (!take(')')) {
                do {
                    arguments.add(parseSum());
                    skipSpaces();
                } while (take(','));

                if (!take(')')) {
                    throw new ExpressionException("Не закрыт список аргументов функции.");
                }
            }

            function.checkArity(name, arguments.size());
            return function.call(arguments);
        }

        private String parseIdentifier() {
            int start = index;
            while (!isFinished() && (Character.isLetterOrDigit(currentChar()) || currentChar() == '_')) {
                index++;
            }
            return source.substring(start, index);
        }

        private double parseNumber() {
            skipSpaces();
            int start = index;
            boolean digitFound = false;

            while (!isFinished() && Character.isDigit(currentChar())) {
                index++;
                digitFound = true;
            }

            if (!isFinished() && currentChar() == '.') {
                index++;
                while (!isFinished() && Character.isDigit(currentChar())) {
                    index++;
                    digitFound = true;
                }
            }

            if (!digitFound) {
                throw new ExpressionException("Ожидалось число на позиции " + index + ".");
            }

            try {
                return Double.parseDouble(source.substring(start, index));
            } catch (NumberFormatException exception) {
                throw new ExpressionException("Некорректная запись числа.");
            }
        }

        private void skipSpaces() {
            while (!isFinished() && Character.isWhitespace(currentChar())) {
                index++;
            }
        }

        private boolean take(char expected) {
            if (!isFinished() && currentChar() == expected) {
                index++;
                return true;
            }
            return false;
        }

        private boolean isFinished() {
            return index >= source.length();
        }

        private char currentChar() {
            return source.charAt(index);
        }
    }
}
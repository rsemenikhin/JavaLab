package ru.mpei.lab2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Консольный запуск интерпретатора выражений.
 */
public final class App {

    private App() {
    }

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionEngine engine = new ExpressionEngine();

        System.out.println("Поддерживаются операции +, -, *, /, ^, скобки, переменные и функции.");
        System.out.print("Введите выражение: ");
        String source = scanner.nextLine();

        try {
            Set<String> variableNames = engine.collectVariables(source);
            if (!variableNames.isEmpty()) {
                System.out.println("Найдены переменные: " + variableNames);
            }
            Map<String, Double> values = askVariableValues(scanner, variableNames);
            double result = engine.evaluate(source, values);
            System.out.println("Результат: " + result);
        } catch (ExpressionException exception) {
            System.out.println("Ошибка: " + exception.getMessage());
        }
    }

    private static Map<String, Double> askVariableValues(Scanner scanner, Set<String> variableNames) {
        Map<String, Double> values = new LinkedHashMap<String, Double>();
        for (String variableName : variableNames) {
            values.put(variableName, readNumber(scanner, variableName));
        }
        return values;
    }

    private static double readNumber(Scanner scanner, String variableName) {
        while (true) {
            System.out.print("Введите значение переменной " + variableName + ": ");
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException exception) {
                System.out.println("Нужно ввести корректное число.");
            }
        }
    }
}
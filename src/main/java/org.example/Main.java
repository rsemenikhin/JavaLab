package org.example;

import java.util.Scanner;

/**
 * Точка входа в приложение для вычисления арифметических выражений.
 */
public class Main {

    /**
     * Запускает консольное приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionCalculator calculator = new ExpressionCalculator();

        System.out.print("Enter expression: ");
        String expression = scanner.nextLine();

        try {
            double result = calculator.evaluate(expression);
            System.out.println("Result: " + result);
        } catch (InvalidExpressionException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
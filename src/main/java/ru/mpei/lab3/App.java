package ru.mpei.lab3;

import java.util.List;

public class App {
    public static void main(String[] args) {
        BenchmarkRunner runner = new BenchmarkRunner();
        List<BenchmarkResult> results = runner.runAll(1000);
        ResultTableFormatter formatter = new ResultTableFormatter();

        System.out.println("ArrayList and LinkedList performance comparison");
        System.out.println(formatter.format(results));
    }
}

package ru.mpei.lab3;

import java.util.List;

public class App {
    public static void main(String[] args) {
        BenchmarkRunner runner = new BenchmarkRunner();
        List<BenchmarkResult> results = runner.runAll(1000);

        for (BenchmarkResult result : results) {
            System.out.println(
                    result.getListType().getTitle()
                            + " "
                            + result.getOperationType().getTitle()
                            + ": "
                            + result.getTimeMillis()
                            + " ms"
            );
        }
    }
}

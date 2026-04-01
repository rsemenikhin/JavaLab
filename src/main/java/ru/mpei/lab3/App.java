package ru.mpei.lab3;

import java.util.List;

public class App {
    private static final int DEFAULT_COUNT = 1000;

    public static void main(String[] args) {
        int count = getCount(args);

        BenchmarkRunner runner = new BenchmarkRunner();
        List<BenchmarkResult> results = runner.runAll(count);
        ResultTableFormatter formatter = new ResultTableFormatter();

        System.out.println("ArrayList and LinkedList performance comparison");
        System.out.println("Operations per method: " + count);
        System.out.println(formatter.format(results));
    }

    static int getCount(String[] args) {
        if (args.length == 0) {
            return DEFAULT_COUNT;
        }

        int count = Integer.parseInt(args[0]);
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than zero");
        }

        return count;
    }
}

package ru.mpei.lab3;

public class App {
    public static void main(String[] args) {
        BenchmarkRunner runner = new BenchmarkRunner();

        BenchmarkResult arrayListResult = runner.runAddBenchmark(ListType.ARRAY_LIST, 1000);
        BenchmarkResult linkedListResult = runner.runAddBenchmark(ListType.LINKED_LIST, 1000);

        System.out.println(arrayListResult.getListType().getTitle() + " add: " + arrayListResult.getTimeMillis() + " ms");
        System.out.println(linkedListResult.getListType().getTitle() + " add: " + linkedListResult.getTimeMillis() + " ms");
    }
}

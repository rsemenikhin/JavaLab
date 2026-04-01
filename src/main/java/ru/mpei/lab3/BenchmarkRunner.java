package ru.mpei.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BenchmarkRunner {
    public List<BenchmarkResult> runAll(int count) {
        List<BenchmarkResult> results = new ArrayList<BenchmarkResult>();

        for (ListType listType : ListType.values()) {
            results.add(runAddBenchmark(listType, count));
            results.add(runGetBenchmark(listType, count));
            results.add(runDeleteBenchmark(listType, count));
        }

        return results;
    }

    public BenchmarkResult runAddBenchmark(ListType listType, int count) {
        List<Integer> list = createList(listType);

        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        long finish = System.nanoTime();

        return new BenchmarkResult(listType, OperationType.ADD, count, finish - start);
    }

    public BenchmarkResult runGetBenchmark(ListType listType, int count) {
        List<Integer> list = fillList(listType, count);

        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            list.get(i);
        }
        long finish = System.nanoTime();

        return new BenchmarkResult(listType, OperationType.GET, count, finish - start);
    }

    public BenchmarkResult runDeleteBenchmark(ListType listType, int count) {
        List<Integer> list = fillList(listType, count);

        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            list.remove(list.size() - 1);
        }
        long finish = System.nanoTime();

        return new BenchmarkResult(listType, OperationType.DELETE, count, finish - start);
    }

    private List<Integer> createList(ListType listType) {
        if (listType == ListType.ARRAY_LIST) {
            return new ArrayList<Integer>();
        }
        return new LinkedList<Integer>();
    }

    private List<Integer> fillList(ListType listType, int count) {
        List<Integer> list = createList(listType);
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        return list;
    }
}

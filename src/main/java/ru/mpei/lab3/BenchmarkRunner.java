package ru.mpei.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BenchmarkRunner {
    public BenchmarkResult runAddBenchmark(ListType listType, int count) {
        List<Integer> list = createList(listType);

        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        long finish = System.nanoTime();

        return new BenchmarkResult(listType, OperationType.ADD, count, finish - start);
    }

    private List<Integer> createList(ListType listType) {
        if (listType == ListType.ARRAY_LIST) {
            return new ArrayList<Integer>();
        }
        return new LinkedList<Integer>();
    }
}

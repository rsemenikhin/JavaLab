package ru.mpei.lab3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResultTableFormatterTest {
    @Test
    public void shouldFormatTable() {
        List<BenchmarkResult> results = Arrays.asList(
                new BenchmarkResult(ListType.ARRAY_LIST, OperationType.ADD, 1000, 1_000_000),
                new BenchmarkResult(ListType.LINKED_LIST, OperationType.GET, 1000, 2_000_000)
        );

        ResultTableFormatter formatter = new ResultTableFormatter();
        String table = formatter.format(results);

        assertTrue(table.contains("ArrayList"));
        assertTrue(table.contains("LinkedList"));
        assertTrue(table.contains("add"));
        assertTrue(table.contains("get"));
    }
}

package ru.mpei.lab3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenchmarkRunnerTest {
    @Test
    public void shouldReturnSixResults() {
        BenchmarkRunner runner = new BenchmarkRunner();

        List<BenchmarkResult> results = runner.runAll(100);

        assertEquals(6, results.size());
    }

    @Test
    public void shouldSaveRequestedCount() {
        BenchmarkRunner runner = new BenchmarkRunner();

        BenchmarkResult result = runner.runAddBenchmark(ListType.ARRAY_LIST, 300);

        assertEquals(300, result.getCount());
        assertEquals(OperationType.ADD, result.getOperationType());
    }
}

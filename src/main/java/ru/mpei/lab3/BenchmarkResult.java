package ru.mpei.lab3;

/**
 * Stores one benchmark result.
 */
public class BenchmarkResult {
    private final ListType listType;
    private final OperationType operationType;
    private final int count;
    private final long timeNanos;

    public BenchmarkResult(ListType listType, OperationType operationType, int count, long timeNanos) {
        this.listType = listType;
        this.operationType = operationType;
        this.count = count;
        this.timeNanos = timeNanos;
    }

    public ListType getListType() {
        return listType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public int getCount() {
        return count;
    }

    public long getTimeNanos() {
        return timeNanos;
    }

    public double getTimeMillis() {
        return timeNanos / 1_000_000.0;
    }
}

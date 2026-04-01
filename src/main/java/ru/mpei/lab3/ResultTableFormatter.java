package ru.mpei.lab3;

import java.util.List;
import java.util.Locale;

public class ResultTableFormatter {
    public String format(List<BenchmarkResult> results) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("| %-10s | %-6s | %-8s | %-10s |%n", "List", "Method", "Count", "Time, ms"));
        builder.append(String.format("| %-10s | %-6s | %-8s | %-10s |%n", "----------", "------", "--------", "----------"));

        for (BenchmarkResult result : results) {
            builder.append(String.format(
                    Locale.US,
                    "| %-10s | %-6s | %-8d | %-10.3f |%n",
                    result.getListType().getTitle(),
                    result.getOperationType().getTitle(),
                    result.getCount(),
                    result.getTimeMillis()
            ));
        }

        return builder.toString();
    }
}

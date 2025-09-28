package org.example.metrics;

public class Metrics {
    private long comparisons = 0;
    private long swaps = 0;
    private int maxDepth = 0;
    private int currentDepth = 0;

    // Считаем сравнения и возвращаем результат
    public boolean comp(int a, int b) {
        comparisons++;
        return a < b;
    }

    public void swap() {
        swaps++;
    }

    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exit() {
        currentDepth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
    public void incComparisons() {
        comparisons++;
    }

    public void incSwaps() {
        swaps++;
    }

    private long allocations = 0;
    public void incAllocations() {
        allocations++;
    }

    public long getAllocations() {
        return allocations;
    }

}


package org.example.metrics;

public class Metrics {
    private int comparisons = 0;
    private int swaps = 0;
    private int allocations = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;

    // вход в рекурсию
    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    // выход из рекурсии
    public void exit() {
        currentDepth--;
    }

    // инкременты
    public void incComparisons() {
        comparisons++;
    }

    public void incSwaps() {
        swaps++;
    }

    public void incAllocations() {
        allocations++;
    }

    // геттеры
    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    public int getAllocations() {
        return allocations;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "comparisons=" + comparisons +
                ", swaps=" + swaps +
                ", allocations=" + allocations +
                ", maxDepth=" + maxDepth +
                '}';
    }
}

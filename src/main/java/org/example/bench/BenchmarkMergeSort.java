package org.example.bench;

import org.example.algorithms.MergeSort;
import org.example.metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Benchmark runner for MergeSort.
 * Produces a CSV with columns:
 * algorithm,input_size,trials,avg_time_ms,avg_comparisons,avg_swaps,avg_allocations,avg_maxDepth
 *
 * Usage: run this class (from IDE or java -cp ...) while your project is compiled.
 */
public class BenchmarkMergeSort {

    private static final Random RNG = new Random(12345);

    public static void main(String[] args) throws IOException {
        int[] sizes = {1000, 5000, 10000, 50000};
        int trials = 5;

        String outFile = "bench_mergesort.csv";
        try (FileWriter fw = new FileWriter(outFile)) {
            fw.write("algorithm,input_size,trials,avg_time_ms,avg_comparisons,avg_swaps,avg_allocations,avg_maxDepth\n");

            for (int n : sizes) {
                long totalTimeMs = 0L;
                long totalComparisons = 0L;
                long totalSwaps = 0L;
                long totalAllocations = 0L;
                long totalMaxDepth = 0L;

                for (int t = 0; t < trials; t++) {
                    int[] a = randomArray(n);
                    int[] copyForVerify = a.clone();

                    Metrics m = new Metrics();
                    long start = System.nanoTime();
                    MergeSort.sort(a, m);
                    long end = System.nanoTime();


                    java.util.Arrays.sort(copyForVerify);
                    if (!java.util.Arrays.equals(a, copyForVerify)) {
                        System.err.println("ERROR: MergeSort produced incorrect result for n=" + n + " trial=" + t);

                    }

                    long timeMs = (end - start) / 1_000_000L;
                    totalTimeMs += timeMs;
                    totalComparisons += m.getComparisons();
                    totalSwaps += m.getSwaps();
                    totalAllocations += m.getAllocations();
                    totalMaxDepth += m.getMaxDepth();

                    try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                }

                double avgTimeMs = (double) totalTimeMs / trials;
                double avgComparisons = (double) totalComparisons / trials;
                double avgSwaps = (double) totalSwaps / trials;
                double avgAllocations = (double) totalAllocations / trials;
                double avgMaxDepth = (double) totalMaxDepth / trials;


                String row = String.format("MergeSort,%d,%d,%.3f,%.2f,%.2f,%.2f,%.2f\n",
                        n, trials, avgTimeMs, avgComparisons, avgSwaps, avgAllocations, avgMaxDepth);
                fw.write(row);
                fw.flush();

                System.out.printf("n=%d | time=%.3f ms | comps=%.0f | swaps=%.0f | allocs=%.0f | depth=%.2f%n",
                        n, avgTimeMs, avgComparisons, avgSwaps, avgAllocations, avgMaxDepth);
            }

            System.out.println("Benchmark finished. CSV file: " + outFile);
        }
    }

    private static int[] randomArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RNG.nextInt(n * 10);
        return a;
    }
}


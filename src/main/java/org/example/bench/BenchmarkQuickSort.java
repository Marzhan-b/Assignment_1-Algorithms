package org.example.bench;

import org.example.algorithms.QuickSort;
import org.example.metrics.Metrics;

import java.io.FileWriter;
import java.util.Arrays;
import java.io.IOException;
import java.util.Random;

public class BenchmarkQuickSort {

    private static final Random RNG = new Random(12345);

    public static void main(String[] args) throws IOException {
        int[] sizes = {1000, 5000, 10000, 50000};
        int trials = 5;

        String outFile = "bench_quicksort.csv";
        try (FileWriter fw = new FileWriter(outFile)) {
            fw.write("algorithm,input_size,trials,avg_time_ms,avg_comparisons,avg_swaps,avg_maxDepth\n");

            for (int n : sizes) {
                long totalTimeMs = 0;
                long totalComp = 0;
                long totalSwap = 0;
                long totalDepth = 0;

                for (int t = 0; t < trials; t++) {
                    int[] a = randomArray(n);
                    int[] copy = a.clone();
                    Metrics m = new Metrics();

                    long start = System.nanoTime();
                    QuickSort.sort(a, m);
                    long end = System.nanoTime();

                    // verify
                    Arrays.sort(copy);
                    if (!Arrays.equals(a, copy)) {
                        System.err.println("ERROR: QuickSort failed for n=" + n);
                    }

                    totalTimeMs += (end - start) / 1_000_000L;
                    totalComp += m.getComparisons();
                    totalSwap += m.getSwaps();
                    totalDepth += m.getMaxDepth();
                }

                double avgTime = (double) totalTimeMs / trials;
                double avgComp = (double) totalComp / trials;
                double avgSwap = (double) totalSwap / trials;
                double avgDepth = (double) totalDepth / trials;

                fw.write(String.format("QuickSort,%d,%d,%.3f,%.2f,%.2f,%.2f\n",
                        n, trials, avgTime, avgComp, avgSwap, avgDepth));
                fw.flush();

                System.out.printf("n=%d | time=%.3f ms | comps=%.0f | swaps=%.0f | depth=%.2f%n",
                        n, avgTime, avgComp, avgSwap, avgDepth);
            }

            System.out.println("Benchmark finished. CSV: " + outFile);
        }
    }

    private static int[] randomArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RNG.nextInt(n * 10);
        return a;
    }
}

package org.example.bench;

import org.example.algorithms.DeterministicSelect;
import org.example.metrics.Metrics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkDeterministicSelect {

    private static final Random RNG = new Random(12345);

    public static void main(String[] args) throws IOException {
        int[] sizes = {1000, 5000, 10000};
        int trials = 5;
        String outFile = "bench_select.csv";

        try (FileWriter fw = new FileWriter(outFile)) {
            fw.write("algorithm,input_size,trials,avg_time_ms,avg_comparisons,avg_swaps,avg_maxDepth\n");

            for (int n : sizes) {
                long totalTime = 0, totalComp = 0, totalSwaps = 0, totalDepth = 0;

                for (int t = 0; t < trials; t++) {
                    int[] a = randomArray(n);
                    Metrics m = new Metrics();
                    long start = System.nanoTime();
                    DeterministicSelect.select(a, n/2, m);
                    long end = System.nanoTime();

                    totalTime += (end - start)/1_000_000;
                    totalComp += m.getComparisons();
                    totalSwaps += m.getSwaps();
                    totalDepth += m.getMaxDepth();
                }

                fw.write(String.format("DeterministicSelect,%d,%d,%.3f,%.2f,%.2f,%.2f\n",
                        n, trials, (double)totalTime/trials,
                        (double)totalComp/trials, (double)totalSwaps/trials,
                        (double)totalDepth/trials));
                fw.flush();
            }
        }
        System.out.println("Benchmark finished: " + outFile);
    }

    private static int[] randomArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RNG.nextInt(n*10);
        return a;
    }
}

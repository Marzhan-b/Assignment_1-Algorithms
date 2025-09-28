package org.example.bench;

import org.example.algorithms.ClosestPair;
import org.example.metrics.Metrics;

import java.util.Random;

public class BenchmarkClosestPair {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000, 10000};
        Random rnd = new Random();

        System.out.printf("%-10s %-10s %-10s%n", "N", "AvgTime(ms)", "MaxDepth");

        for (int n : sizes) {
            long totalTime = 0;
            int maxDepth = 0;
            int trials = 5;

            for (int t = 0; t < trials; t++) {
                int[] x = new int[n];
                int[] y = new int[n];
                for (int i = 0; i < n; i++) {
                    x[i] = rnd.nextInt(1_000_000);
                    y[i] = rnd.nextInt(1_000_000);
                }

                Metrics m = new Metrics();
                long start = System.nanoTime();
                ClosestPair.findClosestPair(x, y, m);
                long end = System.nanoTime();
                totalTime += (end - start) / 1_000_000; // ms
                if (m.getMaxDepth() > maxDepth) maxDepth = m.getMaxDepth();
            }

            System.out.printf("%-10d %-10.2f %-10d%n", n, (totalTime * 1.0 / trials), maxDepth);
        }
    }
}

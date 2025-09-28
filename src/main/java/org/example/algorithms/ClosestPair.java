package org.example.algorithms;

import org.example.metrics.Metrics;

public class ClosestPair {

    /**
     * Simple divide-and-conquer placeholder for Closest Pair.
     * Returns the minimum squared distance between points (x[i], y[i]).
     * Tracks comparisons in Metrics.
     */
    public static int findClosestPair(int[] x, int[] y, Metrics m) {
        int n = x.length;
        if (n < 2) return 0; // not enough points

        int minDist = Integer.MAX_VALUE;
        // Brute-force for simplicity (can be replaced by D&C)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                m.enter();          // recursion depth tracker placeholder
                int dx = x[i] - x[j];
                int dy = y[i] - y[j];
                int dist = dx * dx + dy * dy;
                m.comp(dist, minDist); // count comparison
                if (dist < minDist) {
                    minDist = dist;
                }
                m.exit();
            }
        }
        return minDist;
    }
}

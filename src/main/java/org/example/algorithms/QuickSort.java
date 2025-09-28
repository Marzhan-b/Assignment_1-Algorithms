package org.example.algorithms;

import org.example.metrics.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] a, Metrics m) {
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        m.enter();
        try {
            if (lo >= hi) return;

            // randomized pivot
            int pivotIndex = lo + rand.nextInt(hi - lo + 1);
            int pivot = a[pivotIndex];
            swap(a, pivotIndex, hi, m);

            int p = partition(a, lo, hi, pivot, m);

            // recurse on smaller side first (to bound recursion depth)
            if (p - lo < hi - p) {
                quicksort(a, lo, p - 1, m);
                quicksort(a, p + 1, hi, m);
            } else {
                quicksort(a, p + 1, hi, m);
                quicksort(a, lo, p - 1, m);
            }
        } finally {
            m.exit();
        }
    }

    private static int partition(int[] a, int lo, int hi, int pivot, Metrics m) {
        int i = lo;
        for (int j = lo; j < hi; j++) {
            m.incComparisons();
            if (a[j] < pivot) {
                swap(a, i, j, m);
                i++;
            }
        }
        swap(a, i, hi, m);
        return i;
    }

    private static void swap(int[] a, int i, int j, Metrics m) {
        if (i != j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            m.incSwaps();
        }
    }
}

package org.example.algorithms;

import org.example.metrics.Metrics;
import java.util.Random;

public class QuickSort {

    private static final Random RNG = new Random(12345);

    // Entry point
    public static void sort(int[] a, Metrics m) {
        shuffle(a); // для надежного рандомного pivot
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        if (lo >= hi) return;
        m.enter();
        try {
            int p = partition(a, lo, hi, m);
            // Smaller-first recursion
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

    private static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (m.comp(a[j], pivot)) { // a[j] < pivot
                swap(a, i, j, m);
                i++;
            }
        }
        swap(a, i, hi, m);
        return i;
    }

    private static void swap(int[] a, int i, int j, Metrics m) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        m.swap();
    }

    private static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RNG.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
}

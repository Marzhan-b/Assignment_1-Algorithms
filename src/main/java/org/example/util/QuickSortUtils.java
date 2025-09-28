package org.example.util;

import java.util.Random;
import org.example.metrics.Metrics;

public class QuickSortUtils {
    private static final Random RNG = new Random(12345);

    /** Guard to ensure indices are valid */
    public static void checkIndices(int[] a, int lo, int hi) {
        if (a == null) throw new IllegalArgumentException("Array is null");
        if (lo < 0 || hi >= a.length || lo > hi)
            throw new IllegalArgumentException("Invalid indices: lo=" + lo + ", hi=" + hi);
    }

    /** Swap two elements in the array */
    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /** Partition the array using Lomuto scheme */
    public static int partition(int[] a, int lo, int hi, Metrics m) {
        checkIndices(a, lo, hi);
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (m.comp(a[j], pivot)) { // count comparison
                swap(a, i, j);
                m.swap(); // count swap
                i++;
            }
        }
        swap(a, i, hi);
        m.swap(); // count final swap
        return i;
    }

    /** Shuffle the array for randomized pivot */
    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RNG.nextInt(i + 1);
            swap(a, i, j);
        }
    }
}

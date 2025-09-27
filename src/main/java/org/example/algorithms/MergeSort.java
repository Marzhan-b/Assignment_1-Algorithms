package org.example.algorithms;

import org.example.metrics.Metrics;

public class MergeSort {

    private static final int INSERTION_SORT_CUTOFF = 10;

    public static void sort(int[] a, Metrics m) {
        int[] buf = new int[a.length]; // reusable buffer
        mergesort(a, buf, 0, a.length - 1, m);
    }

    private static void mergesort(int[] a, int[] buf, int lo, int hi, Metrics m) {
        m.enter();
        try {
            if (hi - lo + 1 <= INSERTION_SORT_CUTOFF) {
                insertionSort(a, lo, hi, m);
                return;
            }

            int mid = (lo + hi) / 2;
            mergesort(a, buf, lo, mid, m);
            mergesort(a, buf, mid + 1, hi, m);
            merge(a, buf, lo, mid, hi, m);
        } finally {
            m.exit();
        }
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m) {
        System.arraycopy(a, lo, buf, lo, hi - lo + 1);
        m.incAllocations();

        int i = lo, j = mid + 1, k = lo;

        while (i <= mid && j <= hi) {
            m.incComparisons();
            if (buf[i] <= buf[j]) {
                a[k++] = buf[i++];
            } else {
                a[k++] = buf[j++];
            }
        }

        while (i <= mid) {
            a[k++] = buf[i++];
        }

    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.incComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    m.incSwaps();
                    j--;
                } else break;
            }
            a[j + 1] = key;
        }
    }
}

package org.example.algorithms;

import org.example.metrics.Metrics;

import org.example.metrics.Metrics;

public class DeterministicSelect {

    // Возвращает k-й по величине элемент массива a (0-based), с учётом Metrics
    public static int select(int[] a, int k, Metrics m) {
        if (a.length == 1) return a[0];
        return select(a, 0, a.length - 1, k, m);
    }

    private static int select(int[] a, int left, int right, int k, Metrics m) {
        m.enter();
        try {
            if (left == right) return a[left];

            int pivotIndex = medianOfMedians(a, left, right, m);
            pivotIndex = partition(a, left, right, pivotIndex, m);

            if (k == pivotIndex) {
                return a[k];
            } else if (k < pivotIndex) {
                return select(a, left, pivotIndex - 1, k, m);
            } else {
                return select(a, pivotIndex + 1, right, k, m);
            }
        } finally {
            m.exit();
        }
    }

    private static int partition(int[] a, int left, int right, int pivotIndex, Metrics m) {
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, right, m); // move pivot to end
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (m.comp(a[i], pivotValue)) {
                swap(a, storeIndex, i, m);
                storeIndex++;
            }
        }
        swap(a, storeIndex, right, m); // move pivot to its final place
        return storeIndex;
    }

    private static int medianOfMedians(int[] a, int left, int right, Metrics m) {
        int n = right - left + 1;
        if (n <= 5) {
            insertionSort(a, left, right, m);
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(a, i, subRight, m);
            swap(a, left + numMedians, i + (subRight - i) / 2, m);
            numMedians++;
        }
        return select(a, left, left + numMedians - 1, left + numMedians / 2, m);
    }

    private static void insertionSort(int[] a, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= left && m.comp(key, a[j])) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static void swap(int[] a, int i, int j, Metrics m) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        m.swap();
    }
}

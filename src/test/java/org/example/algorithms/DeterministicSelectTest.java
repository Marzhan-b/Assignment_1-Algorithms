package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] a = {3, 1, 4, 2, 5};
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(a, 3, m); // ищем 3-й элемент
        assertEquals(3, result);
    }

    @Test
    void testLargeRandomArray() {
        int n = 1000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = (int)(Math.random() * n);
        Metrics m = new Metrics();
        int k = n / 2;
        int median = DeterministicSelect.select(a, k, m);
        java.util.Arrays.sort(a);
        assertEquals(a[k], median);
    }
}

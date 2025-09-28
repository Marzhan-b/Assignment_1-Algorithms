package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr, "Array should be sorted");
        assertTrue(m.getMaxDepth() <= 32, "Recursion depth too large");
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertEquals(0, arr.length);
        assertEquals(0, m.getMaxDepth());
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertEquals(1, arr.length);
        assertEquals(0, m.getMaxDepth());
    }
}

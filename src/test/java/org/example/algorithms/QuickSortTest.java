package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSortTest {

    @Test
    void testRandomArray() {
        int[] array = {5, 2, 9, 1, 5, 6};
        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Random array metrics: " + m);
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        int[] expected = {};

        Metrics m = new Metrics();
        QuickSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Empty array metrics: " + m);
    }

    @Test
    void testSingleElement() {
        int[] array = {42};
        int[] expected = {42};

        Metrics m = new Metrics();
        QuickSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Single element metrics: " + m);
    }

    @Test
    void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        Metrics m = new Metrics();
        QuickSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Sorted array metrics: " + m);
    }

    @Test
    void testDuplicates() {
        int[] array = {7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7};

        Metrics m = new Metrics();
        QuickSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Duplicates metrics: " + m);
    }

    @Test
    void testLargeRandomArrayAndDepth() {
        int n = 10000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int)(Math.random() * n);
        }
        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(array, m);

        assertArrayEquals(expected, array);

        int maxAllowedDepth = 2 * (int)(Math.log(n) / Math.log(2)) + 5;
        assertTrue(m.getMaxDepth() <= maxAllowedDepth,
                "Recursion depth too large: " + m.getMaxDepth());
        System.out.println("Large array metrics: " + m);
    }
}

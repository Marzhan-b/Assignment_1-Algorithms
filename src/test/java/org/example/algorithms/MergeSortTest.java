package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testRandomArray() {
        int[] array = {5, 2, 9, 1, 5, 6};
        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Random array metrics: " + m);
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        int[] expected = {};

        Metrics m = new Metrics();
        MergeSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Empty array metrics: " + m);
    }

    @Test
    void testSingleElementArray() {
        int[] array = {42};
        int[] expected = {42};

        Metrics m = new Metrics();
        MergeSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Single element metrics: " + m);
    }

    @Test
    void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        Metrics m = new Metrics();
        MergeSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Already sorted metrics: " + m);
    }

    @Test
    void testAllEqualElements() {
        int[] array = {7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7};

        Metrics m = new Metrics();
        MergeSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("All equal elements metrics: " + m);
    }
    @Test
    void testLargeRandomArrayDepth() {
        int n = 1000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 10000);
        }

        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println("Large random array metrics: " + m);
        System.out.println("Max recursion depth for large array: " + m.getMaxDepth());
    }

}

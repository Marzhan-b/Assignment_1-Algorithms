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
        System.out.println(m);
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        int[] expected = {};

        Metrics m = new Metrics();
        MergeSort.sort(array, m);

        assertArrayEquals(expected, array);
        System.out.println(m);
    }
}

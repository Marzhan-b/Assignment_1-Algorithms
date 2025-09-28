package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void testSmallArray() {
        int[] x = {0, 3, 4};
        int[] y = {0, 0, 0};
        Metrics m = new Metrics();

        int result = ClosestPair.findClosestPair(x, y, m);
        assertEquals(3 * 3, result); // минимальное расстояние между (0,0) и (3,0)
    }

    @Test
    void testTwoPoints() {
        int[] x = {1, 4};
        int[] y = {2, 6};
        Metrics m = new Metrics();

        int result = ClosestPair.findClosestPair(x, y, m);
        assertEquals(25, result); // (1,2) и (4,6) => dx=3, dy=4, dist^2 = 9+16=25
    }

    @Test
    void testSinglePoint() {
        int[] x = {1};
        int[] y = {1};
        Metrics m = new Metrics();

        int result = ClosestPair.findClosestPair(x, y, m);
        assertEquals(0, result); // только одна точка
    }
}

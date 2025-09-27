package org.example;

import org.example.metrics.Metrics;
import org.example.metrics.CsvWriter;

public class Main {
    public static void main(String[] args) {
        Metrics m = new Metrics();

        m.enter();
        try {
            m.incComparisons();
            m.incComparisons();
            m.incSwaps();
            m.incAllocations();
        } finally {
            m.exit();
        }

        System.out.println(m);

        try {
            CsvWriter writer = new CsvWriter("metrics.csv");
            writer.write("TestAlgo", 10, m);
            System.out.println("Metrics saved to metrics.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

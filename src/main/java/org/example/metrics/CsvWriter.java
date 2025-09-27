package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private final String file;

    public CsvWriter(String file) {
        this.file = file;
    }

    public void write(String algoName, int n, Metrics m) throws IOException {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(algoName + "," +
                    n + "," +
                    m.getComparisons() + "," +
                    m.getSwaps() + "," +
                    m.getAllocations() + "," +
                    m.getMaxDepth() + "\n");
        }
    }
}

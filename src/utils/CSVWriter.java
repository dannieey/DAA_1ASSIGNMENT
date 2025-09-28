package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final String filePath;

    public CSVWriter(String filePath) {
        this.filePath = filePath;

        File dir = new File(filePath).getParentFile();
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }
    }

    public void writeHeader() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("Algorithm,ArraySize,Comparisons,Assignments,MaxDepth,Allocations,RunTime(ns)\n");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void writeRow(String algorithm, int arraySize, Metrics metrics) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(String.format("%s,%d,%d,%d,%d,%d,%d\n",
                    algorithm,
                    arraySize,
                    metrics.getComparisons(),
                    metrics.getAssignments(),
                    metrics.getMaxDepth(),
                    metrics.getAllocations(),
                    metrics.getRunTime()
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

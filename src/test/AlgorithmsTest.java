package test;

import models.*;
import utils.CSVWriter;
import utils.Metrics;

import java.util.Random;

public class AlgorithmsTest {
    public static void main(String[] args) {
        CSVWriter csvWriter = new CSVWriter("results/output.csv");
        csvWriter.writeHeader();

        testMergeSort(csvWriter);
        testQuickSort(csvWriter);


        System.out.println("\nAll tests done! Check results/output.csv");
    }

    private static void testMergeSort(CSVWriter writer) {
        Metrics metrics = new Metrics();
        int[] arr = generateRandomArray(1000);
        new MergeSort(metrics).sort(arr);
        writer.writeRow("MergeSort", arr.length, metrics);
        printMetrics("MergeSort", arr.length, metrics);
    }
    private static void testQuickSort(CSVWriter writer) {
        Metrics metrics = new Metrics();
        int[] arr = generateRandomArray(1000);
        new QuickSort(metrics).sort(arr);
        writer.writeRow("QuickSort", arr.length, metrics);
        printMetrics("QuickSort", arr.length, metrics);
    }




    private static void printMetrics(String algoName, int size, Metrics m) {
        System.out.printf("\n--- %s (n=%d) ---\n", algoName, size);
        System.out.printf("Comparisons   : %d\n", m.getComparisons());
        System.out.printf("Assignments   : %d\n", m.getAssignments());
        System.out.printf("MaxDepth      : %d\n", m.getMaxDepth());
        System.out.printf("Allocations   : %d\n", m.getAllocations());
        System.out.printf("RunTime (ns)  : %d\n", m.getRunTime());
    }

    private static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(10000);
        return arr;
    }


}

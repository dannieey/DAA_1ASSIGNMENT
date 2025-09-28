package models;

import utils.Metrics;
import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random rand = new Random();

    public QuickSort(Metrics metrics) { this.metrics = metrics; }

    public void sort(int[] arr) {
        long start = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        metrics.setRunTime(end - start);
    }

    private void quickSort(int[] arr, int low, int high) {
        metrics.incrementCurrentDepth();
        while (low < high) {
            int pivotIndex = partition(arr, low, high);
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
        metrics.decrementCurrentDepth();
    }

    private int partition(int[] arr, int low, int high) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);

        int i = low;
        for (int j = low; j < high; j++) {
            metrics.incrementComparison();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        metrics.incrementAssignments();
    }
}

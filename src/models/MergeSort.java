package models;

import utils.Metrics;

public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 16;
    private final Metrics metrics;

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        metrics.incrementAllocations();
        long start = System.nanoTime();
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);
        long end = System.nanoTime();
        metrics.setRunTime(end - start);
    }

    private void mergeSort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        metrics.enterRecursion();
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right, metrics);
            metrics.exitRecursion();
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid, metrics);
        mergeSort(arr, buffer, mid + 1, right,metrics);
        merge(arr, buffer, left, mid, right, metrics);
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incrementComparison();
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else buffer[k++] = arr[j++];
            metrics.incrementAssignments();
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];
        for (i = left; i <= right; i++) arr[i] = buffer[i];
    }

    private void insertionSort(int[] arr, int left, int right,Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i], j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                metrics.incrementAssignments();
            }
            arr[j + 1] = key;
            metrics.incrementAssignments();
        }
    }
}

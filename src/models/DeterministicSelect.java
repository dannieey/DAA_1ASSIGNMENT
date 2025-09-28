package models;

import utils.Metrics;
import java.util.Arrays;

public class DeterministicSelect {
    private final Metrics metrics;

    public DeterministicSelect(Metrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int k) {
        long start = System.nanoTime();
        int result = selectRec(arr, 0, arr.length - 1, k);
        long end = System.nanoTime();
        metrics.setRunTime(end - start);
        return result;
    }

    private int selectRec(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        int length = pivotIndex - left + 1;
        if (k == length) return arr[pivotIndex];
        else if (k < length) return selectRec(arr, left, pivotIndex - 1, k);
        else return selectRec(arr, pivotIndex + 1, right, k - length);
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }
        int[] medians = new int[(n + 4) / 5];
        for (int i = 0; i < medians.length; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }
        return medianOfMedians(medians, 0, medians.length - 1);
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
                metrics.incrementAssignments();
            }
        }
        return left - 1;
    }
}

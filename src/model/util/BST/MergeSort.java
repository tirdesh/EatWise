package model.util.BST;

import java.util.LinkedList;

public class MergeSort {
    public static LinkedList<String> sort(LinkedList<String> keys) {
        mergeSort(keys, 0, keys.size() - 1);
        return keys;
    }

    private static void mergeSort(LinkedList<String> keys, int left, int right) {
    	System.out.println("Doing Merge Sort:");
        if (left < right) {
            int middle = left + (right - left) / 2;

            mergeSort(keys, left, middle);
            mergeSort(keys, middle + 1, right);

            merge(keys, left, middle, right);
        }
    }

    private static void merge(LinkedList<String> keys, int left, int middle, int right) {
    	System.out.println("Doing Merge");
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays
        String[] leftArray = new String[n1];
        String[] rightArray = new String[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = keys.get(left + i);
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = keys.get(middle + 1 + j);
        }

        // Merge the temporary arrays
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                keys.set(k, leftArray[i]);
                i++;
            } else {
                keys.set(k, rightArray[j]);
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[] if any
        while (i < n1) {
            keys.set(k, leftArray[i]);
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[] if any
        while (j < n2) {
            keys.set(k, rightArray[j]);
            j++;
            k++;
        }
    }
}

package com.jcomm.sorts;

import com.jcomm.datastructures.CollectionsHelper;

public class CycleSort {


    public static void main(String[] args) {
        int[] arr = {-1, 2, 1, 3, 0, 7, 7, 7, 4};
        CycleSort.cycleSort_duplicates(arr);
        CollectionsHelper.printArray(arr);
    }

    public static void cycle_sort_no_duplicates(int arr[]) {
        for (int x = 0; x < arr.length; x++) {
            boolean checkCurrentPosition = true;
            while (checkCurrentPosition) {
                checkCurrentPosition = false;
                int sortedIndex = 0;
                for (int j = x + 1; j < arr.length; j++) {
                    if (arr[x] > arr[j]) {
                        sortedIndex++;
                        checkCurrentPosition = true;
                    }
                }
                if (checkCurrentPosition) {
                    int temp = arr[sortedIndex + x];
                    arr[sortedIndex + x] = arr[x];
                    arr[x] = temp;
                }
            }
        }
    }

    public static void cycleSort_duplicates(int[] arr) {
        for (int x = 0; x < arr.length; x++) {
            boolean checkCurrentPosition = true;
            while (checkCurrentPosition) {
                int sortedIndex = 0;
                checkCurrentPosition = false;
                for (int j = x + 1; j < arr.length; j++) {
                    if (arr[x] >= arr[j]) {
                        sortedIndex++;
                        checkCurrentPosition = true;
                    }
                }
                while (arr[sortedIndex + x] == arr[x] && sortedIndex != 0) {
                    sortedIndex--;
                    if (sortedIndex == 0) {
                        checkCurrentPosition = false;
                    }
                }
                if (checkCurrentPosition) {
                    int temp = arr[sortedIndex + x];
                    arr[sortedIndex + x] = arr[x];
                    arr[x] = temp;
                }
            }
        }
    }
}

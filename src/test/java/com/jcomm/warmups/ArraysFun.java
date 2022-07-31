package com.jcomm.warmups;

import com.jcomm.datastructures.CollectionsHelper;

public class ArraysFun {


    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3};

        cylicSort(arr);
        CollectionsHelper.printArray(arr);
//        System.out.println(max_K_ConsecutiveSum_nTimesK(arr, 2));
    }

    public static int max_K_ConsecutiveSum_nTimesK(int arr[], int k) {

        if (k > arr.length) {
            return -1;
        }

        int maxSum = 0;
        for (int counter = 0; counter + k <= arr.length; counter++) {
            int currentSum = 0;
            for (int j = 0; j < k; j++) {
                currentSum += arr[counter + j];
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static int max_K_ConsecutiveSum_O_of_N(int arr[], int k) {

        if (k > arr.length) {
            return -1;
        }

        int maxSum = 0;
        for (int counter = 0; counter < k; counter++) {
            maxSum += arr[counter];
        }

        for (int j = k; j < arr.length; j++) {
            int currentSum = 0;
            currentSum = maxSum - arr[j - k] + arr[j];
            maxSum = Math.max(maxSum, currentSum);

        }
        return maxSum;
    }

    public static void cylicSort(int arr[]) {
        if (arr == null || arr.length <= 1)
            return;
        for (int idx = 0; idx < arr.length; idx++) {
            int currentAdjustedIdx = idx + 1;
            while (arr[idx] != currentAdjustedIdx) {  //is the val at current position at the correct index
                //doCycle
                int temp = arr[idx];
                int swapValIdx = arr[idx] - 1;
                arr[idx] = arr[swapValIdx];
                arr[swapValIdx] = temp;
            }
        }

    }

}

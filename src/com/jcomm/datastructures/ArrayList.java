/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.datastructures;

/**
 *
 * @author jova
 */
public class ArrayList {

    public static int binarySearch(int[] array, int num) {

        return binarySearch(array, 0, array.length - 1, num);
    }

    private static int binarySearch(int[] array, int lowIndex, int highIndex, int num) {

        int mid = (highIndex + lowIndex) / 2;
        if (lowIndex == highIndex && array[lowIndex] != num) {

            if (num < array[lowIndex]) {
                return (lowIndex * -1);
            } else if (num > array[lowIndex]) {
                return (lowIndex + 1) * -1;
            } else {
                return (lowIndex - 1) * -1;
            }
        }

        if (num == array[mid]) {
            return mid;
        } else if (num < array[mid]) {
            return binarySearch(array, lowIndex, mid, num);
        }

        return binarySearch(array, mid + 1, highIndex, num);
    }

    public static void mergeSort(int[] array) {


        int[] workSpace = new int[array.length];


        recMergeSort(workSpace, 0, array.length - 1, array);

        printIntArray(array);

    }

    private static void recMergeSort(int[] workSpace, int min, int max, int[] array) {

        System.out.println(" Array range is " + min + "-" + max);
        if (min == max) {
            return;
        } else {

            //this will overflow 
            int mid = (min + max) / 2;



            //partions the lower array 
            recMergeSort(workSpace, min, mid, array);

            //partitions the upper array
            recMergeSort(workSpace, mid + 1, max, array);

            //pass the array you want to sort
            mergeSort(workSpace, min, mid + 1, max, array);
        }


    }

    private static void mergeSort(int[] buffer, int lowerIdx, int upperIdx, int upperEnds, int[] array) {

        int bufferIndex = 0; //buffer index 

        int lowerPtr = lowerIdx;
        int lowerArrayEnd = upperIdx - 1;

        int upperPtr = upperIdx;

        while (lowerPtr <= lowerArrayEnd && upperPtr <= upperEnds) {
            if (array[lowerPtr] <= array[upperPtr]) {
                buffer[bufferIndex++] = array[lowerPtr++];
            } else {
                buffer[bufferIndex++] = array[upperPtr++];
            }
        }

        while (lowerPtr <= lowerArrayEnd) {
            buffer[bufferIndex++] = array[lowerPtr++];
        }

        while (upperPtr <= upperEnds) {
            buffer[bufferIndex++] = array[upperPtr++];
        }

        int x = 0;
        for (int i = lowerIdx; i <= upperEnds; i++) {

            array[i] = buffer[x++];

        }

    }

    //helper method to print contents of an array
    private static void printIntArray(int array[]) {

        for (int num : array) {

            System.out.println(num);
        }
    }

    public static void main(String args[]) {

        int[] array = {25, 6, 1, 88, 2};

        int[] sortedArray = {10, 20, 30, 50};
        System.out.println(ArrayList.binarySearch(sortedArray, 49));
    }
}

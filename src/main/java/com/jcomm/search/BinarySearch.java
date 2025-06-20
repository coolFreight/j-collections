package com.jcomm.search;

public class BinarySearch {


    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 5, 8, 33, 19};
        System.out.println(search(arr, 2));
    }

    public static boolean search(int[] arr, int val) {

        return search(arr, arr.length - 1, 0, val);
    }

    private static boolean search(int[] arr, int hi, int lo, int val) {
        if (lo > hi)
            return false;

        int mid = (hi + lo) / 2;

        if(arr[lo] == val || arr[mid] == val || arr[hi] == val)
            return true;

        if (arr[mid] > val) {
            return search(arr, mid - 1, lo, val);
        } else {
            return search(arr, mid+1, mid+1, val);
        }
    }
}

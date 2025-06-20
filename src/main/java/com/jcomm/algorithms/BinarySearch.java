package com.jcomm.algorithms;

public class BinarySearch {

    public int findMin(int[] arr) {
        return findMin(arr, 0, arr.length - 1);
    }

    private int findMin(int[] arr, int lo, int hi) {
        if (lo == hi) {
            return arr[lo];
        }
        int mid = (lo + hi) / 2;
        if (arr[mid] > arr[hi]) {
            return findMin(arr, mid + 1, hi);
        }
        return findMin(arr, lo, mid);
    }

    public static void main(String[] args) {
        var bs = new BinarySearch();
        System.out.println(bs.findMin(new int[]{31, 1, 2, 3, 7, 8, 20}));
    }
}

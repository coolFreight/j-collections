package com.jcomm.sorting;

import java.util.List;

public class MergeSort {


    public static void main(String[] args) {
        int[] arr = {25, 96, 12, 87, 606, 33, 2};
    }

    public static void mergeSort(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid = (lo + hi) / 2 ;
//        var right = merge(arr, lo, mid,);
//        var left = merge(arr, mid+1, hi);



    }

    private static List<Integer> merge(int[] arr, int lo, int hi, List<Integer> right, List<Integer> left) {
        if(lo == hi) {
            return List.of(arr[lo]);
        }

//        while(lo )

        return null;
    }


}

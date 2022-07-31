package com.jcomm.CodingProblems;

import java.util.List;

public class Combinations {
    static int globalTotalAverage = Integer.MAX_VALUE;

    public static int compute(int targetAmt, int arr[], int actualAmt) {
        if (actualAmt >= targetAmt) {
            if (actualAmt - targetAmt < globalTotalAverage) {
                globalTotalAverage = actualAmt - targetAmt;
            }
            return globalTotalAverage;
        }

        for(int x = 0; x < arr.length; x++) {
            compute(targetAmt, arr, actualAmt+arr[x]);
        }
        return globalTotalAverage;
    }

    public static void main(String[] args) {
        int arr[] = {30, 40};
        System.out.println(Combinations.compute(69, arr, 0));
    }





    public static void print(List<Integer> a){

      //  Collections.sort();
//        Collections.sort(a);


    }

}

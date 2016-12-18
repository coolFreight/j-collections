package com.jcomm.warmups;

import com.jcomm.datastructures.CollectionsHelper;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jovaughnlockridge1 on 3/6/16.
 */
public class TwoArraySetIntersection<T extends Comparable> {


    private int arr1Count =0;
    private int arr2Count = 0;


    public static void main(String a []){


        TwoArraySetIntersection<Integer> t = new TwoArraySetIntersection<>();

        Integer [] arr1 = {99, 15, 78, 99, 45,46,45,23, 49,7,2};
        Integer [] arr2 = {7, 99, 4,4,4,4,7,78,78,78,96};




        t.printIntersection(arr1, arr2);


    }

    public T[] printIntersection(T [] arr1, T[] arr2){


        Arrays.sort(arr1);
        Arrays.sort(arr2);
        T outputArray[]= null;

        T lastInsVal = null;
        while(arr1Count < arr1.length &&
                arr2Count < arr2.length){

            if(arr1[arr1Count].compareTo(arr2[arr2Count])==0){
                lastInsVal = arr1[arr1Count];
                System.out.print(arr1[arr1Count] + " ");
                skipDupes(arr1, arr2, lastInsVal);
            }

            if(arr1Count >= arr1.length || arr2Count >= arr2.length)
                return null;

            if(arr1[arr1Count].compareTo(arr2[arr2Count])<0) {
                arr1Count++;
                continue;
            }

            if(arr1[arr1Count].compareTo(arr2[arr2Count]) >0)
                arr2Count++;
                continue;
            }

        return null;
    }


    private void skipDupes(T []arr1, T []arr2,  T lastInsVal){

        while(arr1Count < arr1.length && arr1[arr1Count] == lastInsVal){
            arr1Count++;
        }

        while(arr2Count < arr2.length && arr2[arr2Count] == lastInsVal){
            arr2Count++;
        }
    }
}

package com.jcomm.bitmanipulation;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by jovaughnlockridge1 on 2/20/16.
 */
public class bitmanipulation {




    public static void main (String args []){

        int a =4;
        // prints "2"
        int t = a >>> 4;
        System.out.println(t);



    }


    public void m(){
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.<Integer>reverseOrder());
    }


    public int sum (List<Integer> list){

        int sum = 0;
        for(int x =0; x < list.size(); x++){
            sum += list.get(x);
        }
        return sum;
    }
}

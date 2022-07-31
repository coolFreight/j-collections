package com.jcomm.bitmanipulation;

public class FindOneNonDupilcate {


    public static void main(String arg[]){

        int []nums = {2,1,5,5,98,1,2};
        int res = 0;
        System.out.println("Beginning Loop ");
        for(int n : nums) {
//            System.out.println(n);
            System.out.println("Before res :"+res+" n :"+n);
            res ^= n;
            System.out.println("After res :"+res+" n :"+n);
        }
        System.out.println("End Loop ");
        System.out.println("Answer is ");
        System.out.println(res);

    }
}

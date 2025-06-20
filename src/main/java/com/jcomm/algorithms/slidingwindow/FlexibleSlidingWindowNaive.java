package com.jcomm.algorithms.slidingwindow;

public class FlexibleSlidingWindowNaive {

    public static void main(String[] args) {
        int[] nums = {98, 17, 3, 77, 24, 16, 48, 29};
        int k = 40;
        int maxSum = 0;
        int arrayLength = 0;
        for (int outter = 0; outter < nums.length; outter++) {
            int sum = 0;
            arrayLength = 0;
            int inner = outter;
            while(inner < nums.length && sum + nums[inner] <= k) {
                sum += nums[inner];
                arrayLength++;
                inner++;
            }
            maxSum = Math.max(sum, maxSum);
        }
        System.out.println("Max sum is : " + maxSum + " array length : " + arrayLength);


    }
}

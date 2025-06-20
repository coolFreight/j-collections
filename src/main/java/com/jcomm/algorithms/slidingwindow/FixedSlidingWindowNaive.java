package com.jcomm.algorithms.slidingwindow;


/**
 * N^2 approach
 */
public class FixedSlidingWindowNaive {

    public static void main(String[] args) {
        int [] nums = {1,-7,107,3,-57,98,13,3};

        int maxSum = 0;
        int k = 2;
        for(int outter = 0; outter <= nums.length - k; outter++) {
            int sum = 0;
            for(int inner = 0; inner <= k-1; inner++ ) {
                sum += nums[outter + inner];
            }
            maxSum = Math.max(sum, maxSum);
        }
        System.out.println("Max sum is : "+maxSum);
    }
}

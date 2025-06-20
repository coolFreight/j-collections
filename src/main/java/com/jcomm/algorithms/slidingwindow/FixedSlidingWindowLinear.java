package com.jcomm.algorithms.slidingwindow;

/**
 *
 * https://www.baeldung.com/cs/sliding-window-algorithm
 * Their sliding window of removing previous element still seems weird
 * I have implemented a solution where the there is no need to do any subtraction just
 * adding and comparing at every step.
 *
 */
public class FixedSlidingWindowLinear {

    public static void main(String[] args) {
        int [] nums = {108,5,107,3,-57,98,13,3};

        int maxSum = 0;
        int k = 2;
        int outter = 0;
        for(; outter < k; outter++) {
            maxSum += nums[outter];
        }
        int sum = 0;
        for(int inner = outter; inner <= nums.length - k; inner++ ) {
            maxSum = Math.max(nums[inner] + nums[inner+1], maxSum);
        }
        maxSum = Math.max(sum, maxSum);
        System.out.println("Max sum is : "+maxSum);
    }
}

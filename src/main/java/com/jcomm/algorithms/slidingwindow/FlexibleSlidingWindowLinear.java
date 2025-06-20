package com.jcomm.algorithms.slidingwindow;

public class FlexibleSlidingWindowLinear {
    public static void main(String[] args) {
        int[] nums = {98, 17, 3, 77, 24, 16, 48, 29};
        int k = 40;
        int maxSum = 0;
        int sum = 0;
        int maxArrayLength = 0;
        int arrayLength = 0;
        for (int inner = 0; inner < nums.length; inner++) {
            arrayLength++;
            if (sum + nums[inner] > k) {
                arrayLength = 0;
                sum = 0;
            } else {
                sum += nums[inner];
            }

            if(sum > maxSum) {
                maxSum = sum;
                maxArrayLength = arrayLength;
            }

        }
        System.out.println("Max sum is : " + maxSum + " array length : " + maxArrayLength);


    }
}

package com.jcomm.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DivideAndConquer {


    public static void main(String[] args) {
//        int[] arr = {25, 14, 3, -7, 55};
//        int[] sortedArr = DivideAndConquer.mergeSort(arr);
//
//        Arrays.stream(sortedArr).mapToObj(v -> String.valueOf(v) + ",").forEach(System.out::print);

        System.out.println(DivideAndConquer.judgeCircle("UD"));

    }

    public static boolean judgeCircle(String moves) {
        Map<Character, Integer> mappedMoves = new HashMap<>();
        mappedMoves.put('U', 0);
        mappedMoves.put('D', 0);
        mappedMoves.put('L', 0);
        mappedMoves.put('R', 0);
        for(char move : moves.toCharArray()) {
            Character unMappedMove = getUnMappedMove(move);
            Integer count = mappedMoves.get(unMappedMove);
            if(count > 0) {
                mappedMoves.put(unMappedMove, --count);
            }else {
                mappedMoves.computeIfPresent(move, (k, v) -> ++v);
            }
        }

        if(mappedMoves.values().stream().reduce(0, Integer::sum) > 0) {
            return false;
        }

        return true;

    }

    public static char getUnMappedMove(char move) {
        switch(move) {
            case 'U' :
                return 'D';
            case 'D' :
                return 'U';
            case 'R' :
                return 'L';
        }
        return 'R';
    }

    public static int[] mergeSort(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static int[] mergeSort(int[] arr, int startIndex, int endingIndex) {
        int[] sortedArr;
        if (startIndex == endingIndex) {
            sortedArr = new int[1];
            sortedArr[0] = arr[startIndex];
            return sortedArr;
        }
        int midPoint = (startIndex + endingIndex) / 2;
        int[] leftArray = mergeSort(arr, startIndex, midPoint);
        int[] rightArray = mergeSort(arr, midPoint+1, endingIndex);
        sortedArr = new int[rightArray.length + leftArray.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int sortedArrIndex = 0;
        while (rightIndex < rightArray.length && leftIndex < leftArray.length) {
            int leftValue = leftArray[leftIndex];
            int rightValue = rightArray[rightIndex];
            if (leftValue <= rightValue) {
                sortedArr[sortedArrIndex++] = leftValue;
                leftIndex++;
            } else {
                sortedArr[sortedArrIndex++] = rightValue;
                rightIndex++;
            }
        }
        while (rightIndex < rightArray.length) {
            sortedArr[sortedArrIndex++] = rightArray[rightIndex++];
        }
        while (leftIndex < leftArray.length) {
            sortedArr[sortedArrIndex++] = leftArray[leftIndex++];
        }
        return sortedArr;
    }
}

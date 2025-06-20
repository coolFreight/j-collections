package com.jcomm.whiteboard;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhiteBoard {


//    Write a simple application which, when given a list of integers:
//
//    - Calculates and prints out the mode of the distribution
//   - Prints a bar chart showing the frequency of each element. For the sake of simplicity, the bar chart can be horizontal i.e. with the number on the y axis, and frequency on the x axis. Although a nice to have, the y axis doesn't have to be ordered.
//
//    Consider the time and space complexity of your solution, aiming for one that is O(N) in both cases, where N is the size of the input collection.
//
//    Initial signature: void analyse(Collection<Integer> values);
//
//    Example:
//    input: [1,3,3,1,4,3,3]
//
//    output:
//    mode=3
//            1 | **
//            3 | ****
//            4 | *


    public static void main(String[] args) {

        WhiteBoard w = new WhiteBoard();

        w.analyse(List.of(1, 1, 1, 1, 1, 1, 3, 3, 1, 4, 3, 3));

    }

    public void analyse(Collection<Integer> values) {
        HashMap<Integer, String> mapping = new HashMap<>();

        int highestFreqKey = 0;
        for (Integer num : values) {
            String starCount = mapping.getOrDefault(num, "");
            starCount += "*";
            mapping.put(num, starCount);
        }

        System.out.println("mode=" + highestFreqKey);
        for (Map.Entry<Integer, String> entry : mapping.entrySet()) {
            Integer key = entry.getKey();
            String val = entry.getValue();

            System.out.print(key + " | ");
            System.out.print(val);
            System.out.println();
        }
    }
}

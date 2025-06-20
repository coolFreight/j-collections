package com.jcomm.whiteboard;


import com.jcomm.datastructures.CollectionsHelper;

import java.util.ArrayList;
import java.util.List;

public class Lexigraphical {

    public static void main(String[] args) {
        Lexigraphical l = new Lexigraphical();
        CollectionsHelper.printCollection(l.lexicalOrder(13));
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            int currentNumber = i;

            if(currentNumber > n) {
                continue;
            }
            nums.add(currentNumber);
            while (currentNumber * 10 <= n) {
                currentNumber *= 10;
                nums.add(currentNumber);
            }

            while (currentNumber >= 10) {
                int batchLeadNumFlip = (currentNumber + 9) - (currentNumber % 10);
                int stop = Math.min(batchLeadNumFlip, n);
                while (currentNumber + 1 <= stop) {
                    nums.add(++currentNumber);
                }

                while(currentNumber == n || (currentNumber - 9) % 10 == 0) {
                    currentNumber = currentNumber / 10;
                }

                if( currentNumber < 10 ) {
                    break;
                } else {
                    currentNumber++;
                    nums.add(currentNumber);
                    while(currentNumber * 10 < n){
                        currentNumber *= 10;
                        nums.add(currentNumber);
                    }
                }
            }
        }
        return nums;
    }
}

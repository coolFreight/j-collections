package com.jcomm.algorithms;

import java.util.*;

public class AmazonSticks {

    public static void main(String[] args) {
        AmazonSticks st = new AmazonSticks();
        List<Integer> input = List.of(3,3,5,8);
//        List<Integer> input = List.of(2,3,3,4,6,8,8,6);
        List<Integer> sticks = new ArrayList<>(input);


        System.out.println(st.maxArea(sticks));
    }


    public int maxArea(List<Integer> sticks) {

        Map<Integer, Integer> freq = new HashMap<>();

        for(Integer stick : sticks) {
            freq.put(stick, freq.getOrDefault(stick, 0)+1);
        }

        sticks.sort(Collections.reverseOrder());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(Integer num : sticks) {
            if(!freq.containsKey(num))
                continue;
            Integer count = freq.get(num);
            while(count >= 2) {
                pq.add(num);
                count -= 2;
            }
            if(count == 1) {
                freq.put(num -1, freq.getOrDefault(num -1, 0)+1);
            }
            freq.remove(num);
        }

        int result = 0;
        while(pq.size() >= 2 && pq.size() % 2 == 0 ) {
            result += pq.remove() * pq.remove();
        }

        return result == 0 ? -1 : result % ((int) Math.pow(10, 9) + 7);
    }
}

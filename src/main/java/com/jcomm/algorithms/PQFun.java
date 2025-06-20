package com.jcomm.algorithms;

import java.util.Collections;
import java.util.PriorityQueue;

public class PQFun {



    public static void main(String[] args) {
        PriorityQueue<Integer> pq  = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(32);
        pq.offer(98);
        pq.offer(32);


        System.out.println(pq.poll());
        System.out.println(pq.poll());

        System.out.println(pq.poll());
        System.out.println(pq.poll());

        Move m = new Move(25, 16);
        Move m2 = new Move(23, 16);

        System.out.println(m.equals(m2));




    }


    record Move(int row, int col) {


    }
}

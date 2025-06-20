package com.jcomm.threads;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by jovaughnlockridge1 on 11/15/15.
 */
public class ThreadTester {

    Queue<Integer> q = new PriorityBlockingQueue<>(3);
    Thread t1;
    Thread t2;
    Thread t3;
    JT j1 = new JT(1);
    JT j2 = new JT(1);
    JT j3 = new JT(1);





    public void print(){

       t1 = new Thread(j1, "1");
        t2 = new Thread(j2, "2");
        t3 = new Thread(j3, "3");

        t1.start();
        t2.start();
        t3.start();

    }


    public static void main(String[] arg){
        ThreadTester tt = new ThreadTester();
        tt.print();
    }
}

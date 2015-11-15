package com.jcomm.threads;

import com.jcomm.datastructures.JBlockingQueue;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jovaughnlockridge1 on 11/15/15.
 */
public class ThreadingTester {




    public static void main(String args[]){


        ExecutorService producer = Executors.newFixedThreadPool(2);
        ExecutorService consumer = Executors.newFixedThreadPool(2);


        JBlockingQueue<Integer> queue = new JBlockingQueue<>(3);
        Scanner sc = new Scanner(System.in);

        printMenu();
        int choice = sc.nextInt();
        while(choice !=0) {
            switch (choice) {
                case 1:
                    System.out.println("Enter number to put on queue");

                    int number = sc.nextInt();

                    producer.submit(new Runnable() {
                        public void run() {
                            queue.addElement(number);
                        }

                    });
                    break;
                case 2:
                    consumer.submit(new Runnable() {
                        public void run() {
                            queue.removeElement();
                        }
                    });

                    break;
                case 3:
                    queue.printItemsOnQueue();
                    break;

            }
            printMenu();
            choice = sc.nextInt();
        }

    }


    public static void printMenu(){

        System.out.println("Make your choice ");
        System.out.println("1) put an Integer on the queue.");
        System.out.println("2) remove object off queue.");
        System.out.println("3) pring the queue.");
        System.out.println();


    }
}

package com.jcomm.datastructures;

import com.javamex.classmexer.MemoryUtil;
import com.jcomm.threads.Bone;
import com.jcomm.threads.Dog;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jovaughnlockridge1 on 9/21/15.
 */
public class ArrayFun {


private int a[] = new int[6];


    public static void main(String arg[]){

        Dog d = new Dog();
        Bone b = new Bone();
        //b.setDog(d);
        //d.setBone(b);

        String g = "";

        byte l = 0;




        System.out.println(MemoryUtil.deepMemoryUsageOf(new char[0]));

        displaySizeMinAndMax(Byte.TYPE, Byte.SIZE, Byte.MIN_VALUE, Byte.MAX_VALUE);
        displaySizeMinAndMax(Short.TYPE, Short.SIZE, Short.MIN_VALUE, Short.MAX_VALUE);
        displaySizeMinAndMax(Character.TYPE, Character.SIZE, (int) Character.MIN_VALUE, (int) Character.MAX_VALUE);
        displaySizeMinAndMax(Integer.TYPE, Integer.SIZE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        displaySizeMinAndMax(Long.TYPE, Long.SIZE, Long.MIN_VALUE, Long.MAX_VALUE);
        displaySizeMinAndMax(Float.TYPE, Float.SIZE, Float.MIN_VALUE, Float.MAX_VALUE);
        displaySizeMinAndMax(Double.TYPE, Double.SIZE, Double.MIN_VALUE, Double.MAX_VALUE);


    }


    public static void displaySizeMinAndMax(Class<?> type, int size, Number min, Number max) {
        System.out.printf("type:%-6s size:%-2s min:%-20s max:%s\n", type, size, min, max);
    }


    /**
     * Created by jovaughnlockridge1 on 11/15/15.
     */
    public static class JBlockingQueueTester {




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
}

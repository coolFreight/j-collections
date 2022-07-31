package com.jcomm.datastructures;

import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//import javax.annotation.concurrent.GuardedBy;
//import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class JBlockingQueue<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBlockingQueue.class);
    @GuardedBy("lock")
    private T arr[];
    private int numOfElements;
    private int front = 0;
    private int back = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition pollingCondition = lock.newCondition();
    private Condition offerCondition = lock.newCondition();
    private final int SIZE;


    public JBlockingQueue(int size) {
        this.SIZE = size;
        arr = (T[]) new Object[size];
    }

    public void addElement(T element) {
        try {
            lock.lock();
            while (isFull()) {//this thread checks to see if it can do any useful work

                LOGGER.info("Queue is full.  No action can be taken");
                offerCondition.await();//it gives up the lock and put into a wait queue
                //when the thread is put back into a running state it will check again
                //if it can do any useful work
                //KEEP IN MIND it has acquired the lock again without you calling the lock() method
            }
            arr[back] = element;
            back = (back + 1)% SIZE;
            numOfElements++;
            LOGGER.info("Element add " + element);
            pollingCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T removeElement() {

        T element = null;
        try {
            lock.lock();
            while (isEmpty()) {
                pollingCondition.await();
            }
            element = (T) arr[front];
            LOGGER.info("Removed element : " + element);
            front = (front + 1) % SIZE;
            numOfElements--;
            offerCondition.signalAll();
            return element;

        } catch (InterruptedException e) {
            return null;

        } finally {
            lock.unlock();
        }

    }

    public boolean isFull() {
        try {
            lock.lock();
            return numOfElements == SIZE;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {

        try {
            lock.lock();
            return  numOfElements == 0;
        } finally {
            lock.unlock();
        }

    }

    public int size() {
        try {
            lock.lock();
            return SIZE;
        } finally {
            lock.unlock();
        }
    }

    public T peek(){
        T element = null;
        try {
            lock.lock();
            while (isEmpty()) {
                return null;
            }
            element = (T) arr[front];
            return element;

        }finally {
            lock.unlock();
        }
    }


    public void printItemsOnQueue() {
        CollectionsHelper.printArray(arr);
    }

    public static void main(String[] args) {
        JBlockingQueue<String> q = new JBlockingQueue<>(5);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a command");
        String v = sc.nextLine();
        while (!"q".trim().equalsIgnoreCase(v)) {
            switch(v) {
                case "add":
                    System.out.println("Enter a value to enqueue");
                    String value  = sc.nextLine();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Enqueing ......");
                            q.addElement(value);
                            System.out.println("Done Enqueing ......");
                        }
                    }).start();
                    break;
                case "remove" :
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Remove element ......");
                            String removedElement = q.removeElement();
                            System.out.println("Removed element "+removedElement);
                        }
                    }).start();
                    break;
                case "print":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Printing .....");
                            q.printItemsOnQueue();
                            System.out.println("Done Printing");
                        }
                    }).start();
                    break;
                case "peek":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Peek .....");
                            String peek = q.peek();
                            System.out.println("Peek value "+peek);
                        }
                    }).start();
                    break;
                default :
                    System.out.println("Unknown command "+v);
            }
            System.out.println("Enter a command");
            v = sc.nextLine();
        }

    }
}

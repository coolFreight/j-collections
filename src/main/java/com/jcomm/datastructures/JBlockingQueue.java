package com.jcomm.datastructures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class JBlockingQueue<T> {

    private static final Logger logger = LoggerFactory.getLogger(JBlockingQueue.class);
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

            //acquires the lock
            lock.lock();
            while (isFull()) {//this thread checks to see if can do any useful work

                logger.info("Queue is full.  No action can be taken");
                offerCondition.await();//it gives up the lock and put into a wait queue
                //when the thread is put back into a running state it will check again
                //if it can do any useful work
                //KEEP IN MIND it has acquired the lock again without you calling the lock() method
            }

            arr[back % SIZE] = element;
            numOfElements++;
            back++;
            logger.info("Element add " + element);
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
            logger.info("Removed element : " + element);
            front++;
            numOfElements--;
            offerCondition.signal();
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
            return front == back;
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


    public void printItemsOnQueue() {
        CollectionsHelper.printArray(arr);
    }
}

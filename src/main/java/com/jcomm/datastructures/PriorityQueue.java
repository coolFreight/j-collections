package com.jcomm.datastructures;

import java.lang.reflect.Array;

/**
 * Created by jova on 5/4/15.
 */
public class PriorityQueue<T extends Comparable> {

    private int size;
    private final int MAX_SIZE;

    private T internalArray [];


//    public PriorityQueue(int size){
//
//
//        this(ClassLoader., size);
//
//    }

    private PriorityQueue(Class<T> c, int size){
        MAX_SIZE = size;
        final T[] a = (T[]) Array.newInstance(c, size);
        internalArray = a;
        this.size = 0;
    }

    public T peek(){
       return internalArray[0];
    }





    public void insert(T elem) throws Exception{
        if(size >= MAX_SIZE)
            throw new Exception("Size limit reached : "+MAX_SIZE);

        internalArray[size] = elem;
        bubbleUp(size, elem);
        size++;
    }

    private int getParent(int index){

        return (int)Math.ceil((double)index/2)-1;
    }

    private T getLeftChild(int index){

        return this.internalArray[index+1];
    }

    private T getRightChild(int index){

        return this.internalArray[index+2];
    }

    private void swapParentChild(int childPos, int parentPos){

        T temp = internalArray[parentPos];
        internalArray[parentPos] = internalArray[childPos];
        internalArray[childPos] = temp;
    }

    private void bubbleUp(int pos, T elem){

        if(pos ==0)
            return;

        T parent = internalArray[getParent(pos)];
        if(parent.compareTo(elem)>0){
            swapParentChild(pos, getParent(pos));
            bubbleUp(getParent(pos), elem);
        }
    }


    public static void main(String a[]) {


       System.out.println(Math.ceil((double)1/2));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer.class, 10);

        System.out.println(pq.peek());
        try {
            pq.insert(20);
            System.out.println(pq.peek());
            pq.insert(16);
            System.out.println(pq.peek());
            pq.insert(12);
            System.out.println(pq.peek());
            pq.insert(9);
            System.out.println(pq.peek());
             pq.insert(25);
            System.out.println(pq.peek());
             pq.insert(1);
            System.out.println(pq.peek());

        }catch(Exception e){
            System.out.println(e.getMessage());
        }




    }
}

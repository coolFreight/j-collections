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

    private int getLeftChild(int index){

        return (2*index)+1;
    }

    private int getRightChild(int index){

        return (2*index)+2;
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

    public T remove(){
        if(size==0){
            System.out.println("Q is empty");
            return null;
        }

        T temp = this.internalArray[0];
        this.internalArray[0] =   this.internalArray[size-1];
        this.internalArray[size-1] = null;
        size--;
        bubbleDown(0);
        return temp;
    }

    private void bubbleDown(int currentIndex){

        int leftChildIndex = getLeftChild(currentIndex);
        T left = this.internalArray[leftChildIndex];


        int rightChildIndex = getRightChild(currentIndex);
        T right  =  this.internalArray[rightChildIndex];

        if(right== null && left==null)
            return;

        T parent = this.internalArray[currentIndex];
        if((right!=null && left!=null)){

            if(left.compareTo(right)<0 && left.compareTo(parent)<0){
                swapParentChild(leftChildIndex, currentIndex);
                bubbleDown(leftChildIndex);
            } else if(left.compareTo(right)>0 && right.compareTo(parent)<0){
                swapParentChild(rightChildIndex, currentIndex);
                bubbleDown(rightChildIndex);
            }
            return;
        }else if ((right == null && left != null) && parent.compareTo(left) > 0){
            swapParentChild(leftChildIndex, currentIndex);
            bubbleDown(leftChildIndex);
        }else if((left == null && right !=null) && parent.compareTo(right)>0){
            swapParentChild(rightChildIndex, currentIndex);
            bubbleDown(rightChildIndex);
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
           Integer value = pq.remove();
            System.out.println("Removed val :"+value+" top of q "+pq.peek());
            value = pq.remove();
            System.out.println("Removed val :"+value+" top of q "+pq.peek());
            value = pq.remove();
            System.out.println("Removed val :"+value+" top of q "+pq.peek());
            value = pq.remove();
            System.out.println("Removed val :"+value+" top of q "+pq.peek());
            value = pq.remove();
            System.out.println("Removed val :"+value+" top of q "+pq.peek());
            value = pq.remove();
            System.out.println("Removed val :"+value+" top of q "+pq.peek());
            value = pq.remove();
            System.out.println("Removed val :"+value+" top of q "+pq.peek());

        }catch(Exception e){
            System.out.println(e.getMessage());
        }




    }
}

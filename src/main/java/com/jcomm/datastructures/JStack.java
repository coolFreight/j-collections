package com.jcomm.datastructures;

import java.util.Random;

public class JStack<T> {
    private T[] stack =  (T[]) new Object[4];
    private int pointer = 0;


    public void push(T item) {
        System.out.println("Adding item "+item);
        if(pointer == stack.length)
            resize(stack.length * 2);
        stack[pointer++] = item;
    }

    public T pop() {
        if(pointer == stack.length/2)
            resize(stack.length/2);
        T item = stack[--pointer];
        System.out.println("Removing item "+item);
        return item;
    }

    public static void main(String[] args) {
       System.out.println(1%2);
    }

    public void resize(int size){
        System.out.println("Resizing for size "+size);
        T[] temp =  (T[]) new Object[size];
        for(int i = 0; i<pointer; i++)
            temp[i] = stack[i];
        stack = temp;
    }
}

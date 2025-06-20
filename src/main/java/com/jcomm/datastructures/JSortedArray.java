package com.jcomm.datastructures;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JSortedArray {
    private final int MAX_SIZE;
    private final int[] array;
    private int lastPopulatedIndex = -1;


    public JSortedArray(int arraySize) {
        this.MAX_SIZE = arraySize;
        array = new int[MAX_SIZE];
    }

    //questions to ask
    // are duplicates allowed ? yes
    //
    public void insert(int element) {
        if (lastPopulatedIndex + 1 > MAX_SIZE) {
            throw new RuntimeException("Max size reached");
        }

        if (lastPopulatedIndex == -1) {
            array[++lastPopulatedIndex] = element;
        } else {
            int idx = 0;
            while (idx <= lastPopulatedIndex) {
                if (element <= array[idx]) { // do you want shift on equal value
                    //shift all elements to right
                    shiftRight(idx);
                    array[idx] = element;
                    lastPopulatedIndex++;
                    break;
                }
                idx++;
            }
            if (idx > lastPopulatedIndex) {
                array[++lastPopulatedIndex] = element;
            }
        }
        printArray();
    }

    public int exist(int value) {
        return exist(value, 0, MAX_SIZE);
    }

    public boolean delete(int value) {
        if (isEmpty() || exist(value) == -1) {
            return false;
        }
        int index = exist(value);
        if (index == lastPopulatedIndex) {
            lastPopulatedIndex--;
        } else {
            shiftLeft(index);
        }
        printArray();
        return true;
    }

    public int max() {
        return array[lastPopulatedIndex];
    }

    public int min() {
        return array[0];
    }



    public boolean isEmpty() {
        return lastPopulatedIndex == -1;
    }

    private int exist(int value, int start, int end) {
        if (start == end || start > end) {
            return array[start] == value ? start : -1;
        }

        int mid = (start + end) / 2;

        if (array[mid] == value) {
            return mid;
        } else if (value > array[mid]) {
            return exist(value, mid + 1, end);
        } else {
            return exist(value, start, mid - 1);
        }
    }

    private void shiftLeft(int shiftLeft) {
        if (shiftLeft != lastPopulatedIndex) {
            for (int index = shiftLeft + 1; index <= lastPopulatedIndex; index++) {
                array[index - 1] = array[index];
            }
        }
        lastPopulatedIndex--;
    }

    //you want to shift starting from the back
    private void shiftRight(int shiftFrom) {
        for (int index = lastPopulatedIndex; index >= shiftFrom; index--) {
            array[index + 1] = array[index];
        }
    }

    public void printArray() {
        for (int index = 0; index <= lastPopulatedIndex; index++) {
            System.out.print(array[index] + ", ");
        }
        System.out.println();
    }

    public Spliterator<Integer> spliterator() {
        return Spliterators.spliterator(array(), 0);
    }

    public Stream<Integer> stream() {
        return StreamSupport.stream(spliterator(), false);
    }


    public int [] array(){
        return array;
    }

    public static void main(String[] args) {
        var sortedArray = new JSortedArray(5);
        sortedArray.insert(43);
        sortedArray.insert(17);
        sortedArray.insert(44);
        sortedArray.insert(42);
        sortedArray.insert(1);


        System.out.println(sortedArray.stream().collect(Averager::new, Averager::accept, Averager::combine).average());

    }
}

package com.jcomm.datastructures;

import java.lang.reflect.Array;
import java.util.Arrays;

//this is a min heap
public class Heap implements PriorityQueue<String, Long> {

    private int size;
    private final int MAX_SIZE;

    private final int branchingFactor = 2;

    private final Task<String, Long>[] internalArray;

    private Heap(int size) {
        MAX_SIZE = size;
        internalArray = (Task<String, Long>[]) Array.newInstance(Task.class, size);
        this.size = 0;
    }

    public String peek() {
        System.out.printf("Peeking at top %s with %nleft child %s  %nright child %s%n", internalArray[0], internalArray[getLeftChild(0)], internalArray[getRightChild(0)]);
        return internalArray[0].value();
    }

    public void insert(Task<String, Long> elem) throws Exception {
        if (size >= MAX_SIZE)
            throw new Exception("Size limit reached : " + MAX_SIZE);
        System.out.printf("Inserting task %s at index %d%n", elem, size);
        internalArray[size] = elem;
        bubbleUp(size, elem);
        size++;
    }

    private int getParent(int index) {
        return (int) Math.ceil((double) index / 2) - 1;
    }

    private int getLeftChild(int index) {
        return (2 * index) + 1;
    }

    private int getRightChild(int index) {
        return (2 * index) + 2;
    }

    private void swapParentChild(int childPos, int parentPos) {
        System.out.printf("Swapping %s at child position %d for %s at parent position %d%n",
                internalArray[childPos], childPos,
                internalArray[parentPos], parentPos);
        Task<String, Long> temp = internalArray[parentPos];
        internalArray[parentPos] = internalArray[childPos];
        internalArray[childPos] = temp;
    }

    private void bubbleUp(int newElementPosition, Task<String, Long> newElem) {
        bubbleUp(newElementPosition, getParent(newElementPosition), newElem);
    }

    private void bubbleUp(int newElementPosition, int elementParentPosition, Task<String, Long> newElem) {
        if (newElementPosition == 0)
            return;
        Task<String, Long> parent = internalArray[elementParentPosition];
        boolean isParentHigher = parent.priority().compareTo(newElem.priority()) > 0;
        if (elementParentPosition == 0 && isParentHigher) {
            swapParentChild(newElementPosition, elementParentPosition);
        } else if (isParentHigher) {
            bubbleUp(newElementPosition, getParent(elementParentPosition), newElem);
            swapParentChild(newElementPosition, elementParentPosition);
        }
    }

    private void bubbleDown(int currentIndex) {
        System.out.printf("Bubbling down for index %d%n", currentIndex);
        if (!hasAnyChildren(currentIndex)) {
            return;
        }
        int lowestPriorityChildIndex = getLeftChild(currentIndex);
        var lowestPriorityChild = internalArray[lowestPriorityChildIndex];

        int index = lowestPriorityChildIndex + 1;
        int count = 1;
        while (count < branchingFactor && index < MAX_SIZE) {
            var currentChild = internalArray[index];
            if (currentChild == null) {
                break;
            }
            if (currentChild.priority() < lowestPriorityChild.priority()) {
                lowestPriorityChild = currentChild;
                lowestPriorityChildIndex = index;
            }
            index++;
            count++;
        }

        if (internalArray[currentIndex].priority().compareTo(lowestPriorityChild.priority()) > 0) {
            swapParentChild(lowestPriorityChildIndex, currentIndex);
        }
        bubbleDown(lowestPriorityChildIndex);
    }

    private boolean hasAnyChildren(int nodeIndex) {
        int lowestPriorityChildIndex = getLeftChild(nodeIndex);
        return MAX_SIZE - nodeIndex > 2 &&
                lowestPriorityChildIndex < MAX_SIZE &&
                internalArray[lowestPriorityChildIndex] != null;
    }

    public String top() {
        if (size == 0) {
            System.out.println("Q is empty");
            return null;
        }

        Task<String, Long> temp = this.internalArray[0];
        this.internalArray[0] = this.internalArray[size - 1];
        this.internalArray[size - 1] = null;
        size--;
        bubbleDown(0);
        return temp.value();
    }

    @Override
    public void update(Task<String, Long> updatedElement) {
        System.out.printf("Updating task %s%n", updatedElement);
        for (int index = 0; index < internalArray.length; index++) {
            var task = internalArray[index];
            if (updatedElement.equals(task)) {
                internalArray[index] = updatedElement;
                bubbleDown(index);
            }
        }
    }

    public void printHeap() {
        System.out.printf("%n%n%n================  Printing  Tree ===================%n");
        int index = 0;
        var parentTask = internalArray[index];
        while(index  < MAX_SIZE &&  parentTask != null) {
            var leftChild = getLeftChild(index) >= MAX_SIZE ? null : internalArray[getLeftChild(index)];
            var rightChild = getRightChild(index) >= MAX_SIZE ? null : internalArray[getRightChild(index)];
            System.out.printf("Looking at parent task %s with %nleft child %s  %nright child %s%n", parentTask, leftChild, rightChild);
            parentTask = internalArray[++index];
        }
    }

    @Override
    public String toString() {
        return "Heap{" +
                "size=" + size +
                ", MAX_SIZE=" + MAX_SIZE +
                ", branchingFactor=" + branchingFactor +
                ", internalArray=" + Arrays.toString(internalArray) +
                '}';
    }

    public static void main(String[] args) throws Exception {
        Heap h = new Heap(10);

        h.insert(new Task<>("bug 10", 10L));
        h.peek();

        h.insert(new Task<>("bug 22", 22L));
        h.peek();

        h.insert(new Task<>("bug 5", 5L));
        h.peek();

        h.update(new Task<>("bug 5", 100L));
        h.peek();

        h.insert(new Task<>("bug 3", 3L));

        h.peek();

        h.insert(new Task<>("bug 4", 4L));
        h.peek();

        h.insert(new Task<>("bug 500", 500L));
        h.peek();


        h.printHeap();
    }

}

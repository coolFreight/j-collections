package com.jcomm.warmups;


import java.util.HashSet;
import java.util.Set;

public class TwoPointerFun {

    public static void main(String[] args) {
        Link<Integer> node1 = new Link<>(1);
        Link<Integer> node2 = new Link<>(2);
        Link<Integer> node3 = new Link<>(3);
        Link<Integer> node4 = new Link<>(4);
        Link<Integer> node5 = new Link<>(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node1);
//        node4.setNext(node5);
//        node5.setNext(node2);


        Link<Integer> ptr = node1;
//        TwoPointerFun.detectAndRemoveCycle(ptr);
        TwoPointerFun. detectAndRemoveLoop(ptr);
        while (ptr.hasNext()) {
            System.out.println(ptr.getElement());
            ptr = ptr.getNext();
        }
    }

    public static void detectAndRemoveCycle(Link<Integer> node) {
        if (node == null || node.next == null || node.next.next == null) {
            System.out.println("No cycle detected ");
            return;
        }
        Link<Integer> slowPtr = node.next;
        Link<Integer> fastPtr = node.next.next;
        while (fastPtr.next != null && fastPtr.next.next != null) {
            if (slowPtr == fastPtr) {
                System.out.println("cycle detected ");
                removeCycle(node, slowPtr);
                break;
            }
            slowPtr = slowPtr.next;
            if (fastPtr.next != null && fastPtr.next.next != null) {
                fastPtr = fastPtr.next.next;
            } else {
                fastPtr = fastPtr.next;
            }
        }
    }

    private static void removeCycle(Link<Integer> node, Link<Integer> markedNode) {
        Link<Integer> checkPtr = node.next;
        Link<Integer> fastPtr = markedNode;
        boolean foundCycle = false;
        while (!foundCycle) {
            do {
                Link<Integer> prevPtr = fastPtr;
                fastPtr = fastPtr.next;
                if (fastPtr == checkPtr) {
                    prevPtr.setNext(null);
                    foundCycle = true;
                    break;
                }
                prevPtr = fastPtr;
                fastPtr = fastPtr.next.next;
                if (fastPtr == checkPtr) {
                    prevPtr.setNext(null);
                    foundCycle = true;
                    break;
                }
            } while (fastPtr != markedNode);
            checkPtr = checkPtr.next;
        }
    }


    //
    // Function that detects loop in the list
    private static void detectAndRemoveLoop(Link<Integer> node) {

        // If list is empty or has only one node
        // without loop
        if (node == null || node.next == null)
            return;

        Link slow = node, fast = node;

        // Move slow and fast 1 and 2 steps
        // ahead respectively.
        slow = slow.next;
        fast = fast.next.next;

        // Search for loop using slow and fast pointers
        while (fast != null && fast.next != null) {
            if (slow == fast)
                break;

            slow = slow.next;
            fast = fast.next.next;
        }

        /* If loop exists */
        if (slow == fast) {
            slow = node;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }

            /* since fast->next is the looping point */
            fast.next = null; /* remove loop */
        }
    }



    private static class Link<P> {

        public P element;
        public Link next = null;

        public Link(P element) {
            this.element = element;
        }

        public void setElement(P element) {
            this.element = element;

        }

        public P getElement() {
            return element;
        }

        public void setNext(Link<P> next) {
            this.next = next;

        }

        public Link<P> getNext() {
            return next;

        }

        public boolean hasNext() {
            return next != null;
        }
    }

}

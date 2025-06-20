package com.jcomm.whiteboard;

import com.jcomm.leetcode.utils.LeetCodeSolution;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class RandomSet implements LeetCodeSolution {

    Map<Integer, Node> cache = new HashMap<>();
    private final Node[] values = new Node[200001]; // index -> val
    private final Random r = new Random();
    LinkList list = new LinkList();

    private int totalNodeCount = 0;

    private final int availableNodes = 0;
    private final int countPushes = 0;



    public RandomSet() {
    }

    public boolean insert(int val) {
        if(!cache.containsKey(val)) {
            push(val);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if(cache.containsKey(val)) {
            unlink(val);
            return true;
        }
//        System.out.println("remove : "+val + " false");
        return false;
    }

    private Node push(int val) {
//        System.out.println("add value: "+ val);
        Node node;
        if(totalNodeCount - list.count > 0) {
            node = values[list.count];
            node.val = val;
            if (list.head == null) {
                list.head = node;
                list.tail = node;
            } else {
                Node currTail = list.tail;
                node.prev = currTail;
                currTail.next = node;
                list.tail = node;
            }
            cache.put(val, node);
            list.count++;
        }else {
            node = new Node(val);
            totalNodeCount++;
            if (list.head == null) {
                list.head = node;
                list.tail = node;
            } else {
                Node currTail = list.tail;
                node.prev = currTail;
                currTail.next = node;
                list.tail = node;
            }
            cache.put(val, node);
            values[list.count++] = node;
        }

        return node;
    }

    private void unlink(Integer val) {
//        System.out.println("delete value: "+ val);
        unlink(cache.remove(val));
    }

    private void unlink(Node node) {
//        System.out.println("unlink value: "+ node.val);
        if(node == list.head && node == list.tail) {
            list.head = null;
            list.tail = null;

        }else {

            if(list.tail.val == node.val) {
               Node tailPrev = node.prev;
               tailPrev.next = null;
               list.tail = tailPrev;
               node.prev = null;
            } else {
                node.val = list.tail.val;

                Node temp = list.tail.prev;
                if (temp != null) {
                    temp.next = null;
                }
                list.tail = temp;
                cache.put(node.val, node);
            }
        }
        list.count--;
    }


    public int getRandom() {
        return values[r.nextInt(list.count)].val;
    }

    class Node {
        int val;
        Node prev;
        Node next;
        public Node(int val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(val, node.val);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + val +
                    '}';
        }
    }

    class LinkList {
        private Node head;
        private Node tail;
        int count = 0;
    }


    public static void main(String[] args) {
        RandomSet rs = new RandomSet();

        rs.insert(0);
        rs.insert(1);
        rs.remove(0);
        print(rs.list.head);
        rs.insert(2);
        print(rs.list.head);
        rs.remove(1);
        print(rs.list.head);

        System.out.println(rs.getRandom());
    }


    private static void print(Node node) {
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }
        System.out.println();
    }

}

package com.jcomm.whiteboard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LFUCache {

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(10);
        try {
            longSetup(lfuCache);
//            lfuCache.put(1, 1);
//            lfuCache.put(2, 2);
//            lfuCache.get(1);
//            lfuCache.put(3, 3);
//            lfuCache.get(2);
//            lfuCache.get(3);
//            lfuCache.put(4, 4);
//            lfuCache.get(1);
//            lfuCache.get(3);
//            lfuCache.get(4);


            System.out.println(lfuCache.sb);
            System.out.println("Passed "+lfuCache.sb.toString().equalsIgnoreCase("null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("############################## KABOOM #######################");
            System.out.println("counters " + lfuCache.counter);
            System.out.println("list contains :");
            System.out.println("LOWEST_FREQ " + LOWEST_FREQ);
            print(lfuCache.countToList);

                System.out.println(lfuCache.sb);

        }
//        print(lfuCache.head);
    }

    private final Map<Integer, Node> nodes = new HashMap<>();
    private final Map<Integer, Integer> counter = new HashMap<>();


    //count maps list
    private final Map<Integer, DLNode> countToList = new HashMap<>();
    static int LOWEST_FREQ = 1;

    private final int MAX_CAPACITY;

    StringBuilder sb = new StringBuilder();


    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException();
        }
        this.MAX_CAPACITY = capacity;
        sb.append("null,");

    }

    public int get(int key) {
        System.out.println("get: " + key);
        var node = nodes.remove(key);

        if (node == null) {
            sb.append("-1,");
            return -1;
        }

        Integer currentCount = counter.get(node.key);
        DLNode dlNode = countToList.get(currentCount);
        unlink(dlNode, node);

        if(LOWEST_FREQ == currentCount  && dlNode.count == 0) {
            LOWEST_FREQ++;
        }
        updatedBookKeeping(node);

        DLNode dlNodeUpdate = countToList.get(currentCount+1);
        if (dlNodeUpdate == null) {
            dlNodeUpdate = new DLNode();
            countToList.put(currentCount+1, dlNodeUpdate);
        }

        var val = push(dlNodeUpdate, node).value;


        sb.append(val + ",");
        print(countToList);
        return val;

    }

    public void put(int name, int value) {
        System.out.println("insert key: " + name + " value: " + value);
        Node node;
        if (nodes.containsKey(name)) {
            node = nodes.get(name);
            DLNode dlNode = countToList.get(counter.get(node.key));
            unlink(dlNode, node);
            if(dlNode.head == null) {
                countToList.remove(counter.get(node.key));

            }
            if(dlNode.count == 0 && counter.get(node.key) == LOWEST_FREQ) {
                LOWEST_FREQ++;
            }
            node.value = value;
        } else {
            if (nodes.size() == MAX_CAPACITY) {
                System.out.println("AT MAX ");
                System.out.println("counters " + counter);
                System.out.println("list contains :");
                print(countToList);
                System.out.println("LOWEST_FREQ " + LOWEST_FREQ);

                DLNode dlNode = countToList.get(LOWEST_FREQ);
                Node evictedNode = dlNode.head;
                unlink(dlNode, evictedNode);
                counter.remove(evictedNode.key);
                nodes.remove(evictedNode.key);
                System.out.println("evicted node :" + evictedNode);
                LOWEST_FREQ = 1;
            }
            node = new Node(name, value);
        }

        updatedBookKeeping(node);

        Integer currentCount = counter.get(node.key);
        DLNode updatedDlNode = countToList.get(currentCount);
        if (updatedDlNode == null) {
            updatedDlNode = new DLNode();
            countToList.put(currentCount, updatedDlNode);
        }
        push(updatedDlNode, node);


        sb.append("null,");
        print(countToList);
    }

    private void unlink(DLNode dlNode, Node node) {
        if (dlNode.head == node) {
            dlNode.head = node.next;
            if (dlNode.head != null) {
                dlNode.head.prev = null;
            } else {
                dlNode.tail = null;
            }
        } else {
            if (node == dlNode.tail) {
                dlNode.tail = node.prev;
                node.prev = null;
                dlNode.tail.next = null;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
            }
        }
        dlNode.count--;
        node.next = null;
        node.prev = null;
    }

    private Node push(DLNode dlNode, Node newest) {
        if (dlNode.head == null) {
            dlNode.head = newest;
            dlNode.tail = newest;
        } else {
            dlNode.tail.next = newest;
            var tempNode = dlNode.tail;
            dlNode.tail = newest;
            dlNode.tail.prev = tempNode;
        }
        dlNode.count++;
        nodes.put(newest.key, newest);

//        Integer.
        return newest;
    }


    private void updatedBookKeeping(Node node) {
        int currentFreqCount = counter.getOrDefault(node.key, 0);
        int updatedFreqCount = currentFreqCount + 1;
        counter.put(node.key, updatedFreqCount);
        if (currentFreqCount == 0) {
            if (LOWEST_FREQ > 1) {
                LOWEST_FREQ = 1;
            }
        }
    }

    private static class DLNode {
        Node head, tail = null;
        int count = 0;

    }

    static class Node {
        Integer key;
        Integer value;
        Node next;
        Node prev;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    private static void print(Map<Integer, DLNode> map) {
        System.out.println("LOWEST_FREQ "+LOWEST_FREQ);

        Set<Integer> counts = map.keySet();
        for(Integer count : counts) {
            Node node = map.get(count).head;
            System.out.print("List for itmes with count "+count+": ");
            while (node != null) {
                System.out.print(node.key + " -> ");
                node = node.next;
            }
            System.out.println();
        }

    }


    public static void longSetup(LFUCache lfuCache) {
        lfuCache.put(10, 13);
        lfuCache.put(3, 17);
        lfuCache.put(6, 11);
        lfuCache.put(10, 5);
        lfuCache.put(9, 10);

        lfuCache.get(13);

        lfuCache.put(2, 19);
        lfuCache.get(2);
        lfuCache.get(3);

        lfuCache.put(5, 25);
        lfuCache.get(8);
        lfuCache.put(9, 22);
        lfuCache.put(5, 5);
        lfuCache.put(1, 30);
        lfuCache.get(11);

        lfuCache.put(9, 12);
        lfuCache.get(7);
        lfuCache.get(5);
        lfuCache.get(8);
        lfuCache.get(9);
        lfuCache.put(4, 30);

        lfuCache.put(9, 3);
        lfuCache.get(9);
        lfuCache.get(10);
        lfuCache.get(10);
        lfuCache.put(6, 14);
        lfuCache.put(3, 1);
        lfuCache.get(3);
        lfuCache.put(10, 11);
        lfuCache.get(8);
        lfuCache.put(2, 14);
        lfuCache.get(1);

        lfuCache.get(5);
        lfuCache.get(4);
        lfuCache.put(11, 4);
        lfuCache.put(12, 24);
        lfuCache.put(5, 18);
        lfuCache.get(13);
        lfuCache.put(7, 23);
        lfuCache.get(8);
        lfuCache.get(12);
        lfuCache.put(3, 27);
        lfuCache.put(2, 12);
        lfuCache.get(5);
        lfuCache.put(2, 9);
        lfuCache.put(13, 4);
        lfuCache.put(8, 18);
        lfuCache.put(1, 7);
        lfuCache.get(6);
        lfuCache.put(9, 29);
        lfuCache.put(8, 21);
        lfuCache.get(5);
        lfuCache.put(6, 30);
        lfuCache.put(1, 12);
        lfuCache.get(10);
        lfuCache.put(4, 15);
        lfuCache.put(7, 22);
        lfuCache.put(11, 26);
        lfuCache.put(8, 17);
        lfuCache.put(9, 29);
        lfuCache.get(5);

        lfuCache.put(3, 4);
        lfuCache.put(11, 30);
        lfuCache.get(12);
        lfuCache.put(4, 29);
        lfuCache.get(3);
        lfuCache.get(9);
        lfuCache.get(6);
        lfuCache.put(3, 4);
        lfuCache.get(1);
        lfuCache.get(10);
        lfuCache.put(3, 29);
        lfuCache.put(10, 28);
        lfuCache.put(1, 20);
        lfuCache.put(11, 13);
        lfuCache.get(3);
        lfuCache.put(3, 12);
        lfuCache.put(3, 8);
        lfuCache.put(10, 9);
        lfuCache.put(3, 26);
        lfuCache.get(8);
        lfuCache.get(7);
        lfuCache.get(5);
        lfuCache.put(13, 17);
        lfuCache.put(2, 27);
        lfuCache.put(11, 15);
        lfuCache.get(12);

        lfuCache.put(9, 19);
        lfuCache.put(2, 15);
        lfuCache.put(3, 16);
        lfuCache.get(1);
        lfuCache.put(12,17);
    }
}



package com.jcomm.whiteboard;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created a Least Recently Used cache
 * that updates an elements recency on the following operations
 *
 * insert()
 * get()
 *
 * This implementation is thread safe
 */
public class Cache {

    //Players with a name - value
    //keep most frequently used
    //input in - name

    //eviction policy player a -
    private final Map<String, Node> nodes = new ConcurrentHashMap<>();

    private Node head;

    private Node tail;

    private final int MAX_CAPACITY;

    public Cache(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException();
        }
        this.MAX_CAPACITY = capacity;
    }

    public static void main(String[] args) {
        Cache cache = new Cache(1);

        cache.insert("jo", 5);
        cache.insert("ab", 6);
        cache.insert("d", 25);
        System.out.println(cache.nodes + " - eldest :" + cache.getEldest() + " should be {jo:5}");
        print(cache.head);

        cache.get("jo");
        System.out.println(cache.nodes + " - eldest :" + cache.getEldest() + " get(jo)  should be {ab:6} eldest");
        print(cache.head);

        cache.insert("d", 23);
        System.out.println(cache.nodes + " -  eldest :" + cache.getEldest() + " insert(d, 23)   no change ");
        print(cache.head);


        cache.insert("dpo", 23);
        System.out.println(cache.nodes + " - eldest :" + cache.getEldest() + " insert(dpo, 23) - ab removed");
        print(cache.head);

        cache.insert("jo", 5);
        cache.insert("ab", 6);
        cache.insert("d", 25);
        System.out.println(cache.nodes + " - eldest :" + cache.getEldest() + " new  should be {jo:5}");
        print(cache.head);

        cache.insert("d", 23);
        System.out.println(cache.nodes + " -  eldest :" + cache.getEldest() + " insert(d, 23)   no change ");
        print(cache.head);

        cache.insert("dpo", 23);
        System.out.println(cache.nodes + " - eldest :" + cache.getEldest() + " insert(dpo, 23) - ab removed");
        print(cache.head);
    }


    //tor.hagemann@chase.io
    public synchronized void insert(String name, Integer value) {
        Node node ;
        if (nodes.containsKey(name)) {
            node = nodes.get(name);
            unlink(node);
            node.value = value;
        } else {
            if (nodes.size() == MAX_CAPACITY) {
                delete(getEldest());
            }
            node = new Node(name, value);
        }
        push(node);
    }

    private void delete(Node node) {
        unlink(node);
        nodes.remove(node.key);
    }

    private void unlink(Node node) {
        if (head == node) {
            head = node.next;
            if(head != null) {
                head.prev = null;
            }
        } else {
            if(node == tail) {
                tail = node.prev;
                node.prev = null;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
                if(node.next != null) {
                    node.next.prev =  node.prev;
                }
            }
        }
        node.next = null;
        node.prev = null;
    }

    public Node getEldest() {
        return head;
    }

    public synchronized Node get(String name) {
        var node = nodes.remove(name);
        if (node == null) {
            return node;
        }
        unlink(node);
        return push(node);
    }

    private Node push(Node newest) {
        if (head == null) {
            head = newest;
            tail = newest;
        } else {
            tail.next = newest;
            var tempNode = tail;
            tail = newest;
            tail.prev = tempNode;
        }
        nodes.put(newest.key, newest);
        return newest;
    }

    static class Node {
        String key;
        Integer value;
        Node next;
        Node prev;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public Node(String key, Integer value) {
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

    private static void print(Node node) {
        while (node != null) {
            System.out.print(node.key + ",");
            node = node.next;
        }
        System.out.println();
        System.out.println();
    }

}

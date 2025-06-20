package com.jcomm.whiteboard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PQFun {
    Map<String, Integer> counter = new HashMap<>();
    Map<String, String> cache = new HashMap<>();

    int low = 1;

    int max;

    public PQFun(int max) {
        this.max = max;
    }

    PriorityQueue<String> pr = new PriorityQueue<>((n1, n2) -> {
        Integer first = counter.get(n1);
        Integer second = counter.get(n2);
        if(first < second) {
            return -1;
        }
        return 0;
    });

    public void insert(String name, String value) {

        if(cache.size() == max) {
            String evicted = pr.poll();
            System.out.println(evicted +" was removed from cache");
            counter.remove(evicted);
            cache.remove(evicted);
            low = counter.get(pr.peek());
        }

        if(cache.containsKey(name)) {
            pr.remove(name);
        }

        var count = counter.getOrDefault(name, 0)+1;
        low = Math.min(count, low);
        counter.put(name, counter.getOrDefault(name, 0)+1);
        cache.put(name, value);
        pr.add(name);
        System.out.println("insert :"+name+" top of heap "+pr.peek());
    }

    public String get(String name) {
        if(!cache.containsKey(name)) {
            System.out.println(name+" does not exist");
            return "not exist";
        }
        pr.remove(name);
        counter.put(name, counter.getOrDefault(name, 0)+1);

        pr.add(name);
        System.out.println("get name "+ name+" top of heap "+pr.peek());
        return cache.get(name);
    }

    public static void main(String[] args) {

        PQFun pqFun = new PQFun(3);

        pqFun.insert("j", "bl");
        pqFun.insert("jded", "bl");
        pqFun.insert("bob", "bl");

        pqFun.get("j");

        pqFun.get("556");



    }
}

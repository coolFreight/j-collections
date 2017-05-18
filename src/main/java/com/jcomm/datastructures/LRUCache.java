package com.jcomm.datastructures;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jovaughnlockridge1 on 3/23/17.
 */
public class LRUCache {

    private static final int MAX_ENTRIES = 3;
    private LinkedHashMap<String, String> map = new LinkedHashMap<String, String>() {

        protected boolean removeEldestEntry(Map.Entry eldest) {
            return map.size() > MAX_ENTRIES;
        }
    };


    public String getItem(String item) {

        String result = map.remove(item);
        if (result == null) {
            System.out.println("Cache Miss ");
            // got to back store
            result = "Random" + Math.random();
        }

        map.put(item, result);

        return result;
    }

    public void printCacheElements(){

        map.entrySet().stream().forEach(e -> System.out.println("Key="+e.getKey()+", Value="+e.getValue()));
    }
}

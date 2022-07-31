package com.jcomm.datastructures;

import org.junit.jupiter.api.Test;

/**
 * Created by jovaughnlockridge1 on 3/23/17.
 */
public class LRUCacheTest {

    @Test
    public void should_removeLeastRecentlyUsed_whenCacheIsFull(){

        LRUCache cache = new LRUCache();
        cache.getItem("J");
        cache.printCacheElements();

        cache.getItem("M");
        cache.printCacheElements();

        cache.getItem("Z");
        cache.printCacheElements();

        cache.getItem("T");
        cache.printCacheElements();


    }
}

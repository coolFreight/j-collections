package com.jcomm.datastructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSortedArrayTest {

    private final JSortedArray sortedArray = new JSortedArray(5);

    @Test
    public void testInsertion() {
        sortedArray.insert(43);
        assertArray(sortedArray.array(), new int[] {43});
        sortedArray.insert(17);
        assertArray(sortedArray.array(), new int[] {17, 43});
        sortedArray.insert(44);
        assertArray(sortedArray.array(), new int[] {17, 43, 44});
    }

    private void assertArray(int [] array, int [] expectedArray) {
        for(int index = 0; index < expectedArray.length; index++) {
            assertEquals(array[index], expectedArray[index]);
        }
    }
}

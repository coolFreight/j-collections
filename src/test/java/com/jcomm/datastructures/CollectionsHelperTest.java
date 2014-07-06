package com.jcomm.datastructures;



import org.junit.Assert;
import org.junit.Test;

import com.jcomm.trees.HeapSort;

public class CollectionsHelperTest {
	
	@Test
	public void testSwapValues(){
		
		Integer [] array = new Integer[2];

		array[0] = 35;
		array[1] = 2;
			
		Assert.assertEquals(array[0],  Integer.valueOf(35));
		Assert.assertEquals(array[1],  Integer.valueOf(2));
		CollectionsHelper.swapValues(array, 0, 1);
		Assert.assertEquals(array[0],  Integer.valueOf(2));
		Assert.assertEquals(array[1],  Integer.valueOf(35));
	}
	
	@Test
	public void testHeapSortSortedInAscendingOrder(){

		Integer A[] = {100,23,90,55,32,-2,9};
		CollectionsHelper.heapSort(A);
		int highestVal = A[0];
		for(int x = 1; x<A.length; x++){		
			if(highestVal > A[x]){
				Assert.fail();
			}
		}
		CollectionsHelper.printArray(A);
	}

}

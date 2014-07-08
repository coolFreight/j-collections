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
		int highestVal;
		for(int x = 0; x<A.length-1; x++){	
			highestVal = A[x+1];
			if(highestVal < A[x]){
				Assert.fail(highestVal+" is not greater than "+A[x]);	
			}
			
		}
		CollectionsHelper.printArray(A);
	}

	@Test
	public void testQuickSortSortedInAscendingOrder(){

		int A[] = {100,23,90,55,32,-2,9};
		CollectionsHelper.quickSort(A);
		CollectionsHelper.printArray(A);
		int highestVal;
		for(int x = 0; x<A.length-1; x++){	
			highestVal = A[x+1];
			if(highestVal < A[x]){
				Assert.fail(highestVal+" is not greater than "+A[x]);	
			}
			
		}
		
	}

}

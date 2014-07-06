package com.jcomm.datastructures;



import org.junit.Assert;
import org.junit.Test;

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

}

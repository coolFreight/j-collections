package com.jcomm.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class JArrayListTest {
	
	
	
	@Test
	public void testAddInsertionSort(){
		
		
		//JArrayList<Integer> arrayList = new JArrayList<>();
		
		//arrayList.addInsertSort(25);
		
		//arrayList.
	}
	
	@Test
	public void testJListIterator(){
		
		JArrayList<Integer> arrayList = new JArrayList<>();
		arrayList.addInsertSort(25);
		arrayList.addInsertSort(30);
		
		for(Integer i : arrayList){
			
			switch(i){
			
			case 25:
				Assert.assertEquals(Integer.valueOf(25),  i);
				return;
			case 30:
				Assert.assertEquals(Integer.valueOf(30),  i);
				return;
			default:
				Assert.fail();
				return;
			}
			
		}
	}

}

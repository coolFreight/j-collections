package com.jcomm.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
				assertEquals(Integer.valueOf(25),  i);
				return;
			case 30:
				assertEquals(Integer.valueOf(30),  i);
				return;
			default:
				fail();
				return;
			}
			
		}
	}

}

package com.jcomm.datastructures;

import org.junit.Assert;
import org.junit.Test;

import com.jcomm.exceptions.IllegalOperationException;

public class JLinkedListTest {

	@Test
	public void testGetIndex(){
		
		JLinkedList<Integer> l = new JLinkedList<>();
		l.add(5);
		l.add(3);
		l.add(10);
		
		Assert.assertEquals(Integer.valueOf(5),  l.getValueAtIndex(0));
		l.removeFirst();
		Assert.assertEquals(Integer.valueOf(3),  l.getValueAtIndex(0));
		Assert.assertEquals(Integer.valueOf(10),  l.getValueAtIndex(1));
	}
	
	
	@Test(expected = IllegalOperationException.class)
	public void testGetIndexOutOfBounds(){
		JLinkedList<Integer> l = new JLinkedList<>();
		l.add(5);
		l.add(3);
		l.add(10);
		l.getValueAtIndex(4);
	}

}

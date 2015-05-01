package com.jcomm.datastructures;

import com.jcomm.trees.JTreeNode;
import com.jcomm.trees.Node;
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


	@Test
	public void testReverseList(){

		JLinkedList<Integer> l = new JLinkedList<>();
		l.add(5);
		l.add(3);
		l.add(10);

		CollectionsHelper.printCollection(l);
		l.reverseList();

		CollectionsHelper.printCollection(l);



	}



}

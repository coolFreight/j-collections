package com.jcomm.datastructures;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class JBinarySearchTreeTest {
	private JBinarySearchTree<Integer> bst;
	
	
	@Before
	public void setup(){
		
		bst = new JBinarySearchTree<>();

		
		
	}
	
	@Test
	public void testInorder(){
		
		bst.insert(6);
		bst.insert(2);
		bst.insert(7);
		JList<Integer> l = bst.getNodesInorder();
		Integer c = l.removeFirst();
		Assert.assertEquals(Integer.valueOf(2), c);
		c = l.removeFirst();
		Assert.assertEquals(Integer.valueOf(6), c);
		c = l.removeFirst();
		Assert.assertEquals(Integer.valueOf(7), c);
	}
	
	@Test
	public void testFindValue(){
		bst.insert(6);
		bst.insert(2);
		bst.insert(7);
		Assert.assertTrue(bst.findValue(Integer.valueOf(2)));
		Assert.assertTrue(bst.findValue(Integer.valueOf(6)));
		Assert.assertTrue(bst.findValue(Integer.valueOf(7)));
		Assert.assertFalse(bst.findValue(Integer.valueOf(23)));
	}
	
	@Test 
	public void testRemoveRootItem(){
		bst.insert(9);
		bst.insert(12);
		bst.insert(3);
		bst.insert(15);
		bst.insert(11);
		bst.insert(10);
		bst.insert(2);
		bst.insert(7);		
		bst.remove(9);
		Assert.assertEquals(Integer.valueOf(12), bst.getRootNode().getValue());
	}
	
	@Test 
	public void testRemoveLeftSubtree(){
		bst.insert(9);
		bst.insert(12);
		bst.insert(3);
		bst.insert(15);
		bst.insert(11);
		bst.insert(10);
		bst.insert(2);
		bst.insert(7);		
		bst.remove(3);
		Assert.assertEquals(Integer.valueOf(7), bst.getRootNode().getLeftNode().getValue());
	}
	
	@Test 
	public void testRemoveRightSubtree(){
		bst.insert(9);
		bst.insert(12);
		bst.insert(3);
		bst.insert(15);
		bst.insert(11);
		bst.insert(10);
		bst.insert(2);
		bst.insert(7);		
		bst.remove(12);
		Assert.assertEquals(Integer.valueOf(15), bst.getRootNode().getRightNode().getValue());
	}
	
	
	

}

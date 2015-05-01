package com.jcomm.datastructures;

import com.jcomm.trees.JTree;
import com.jcomm.trees.JTreeNode;
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
	
	@Test 
	public void testFindMin(){
		bst.insert(9);
		bst.insert(12);
		bst.insert(3);
		bst.insert(15);
		bst.insert(11);
		bst.insert(10);
		bst.insert(2);
		bst.insert(7);		
		bst.remove(12);
		Assert.assertEquals(Integer.valueOf(2), bst.findMinimum());
	}
	
	@Test 
	public void testLevelOrder(){
		bst.insert(9);
		bst.insert(12);
		bst.insert(3);
		bst.insert(15);
		bst.insert(11);
		bst.insert(10);
		bst.insert(2);
		bst.insert(7);		
		
		CollectionsHelper.printCollection(bst.getLevelOrder());
	}


	@Test
	public void testKareem(){
		bst.insert(20);
		bst.insert(10);
		bst.insert(40);
		bst.insert(15);
		bst.insert(2);
		bst.insert(7);

		bst.printIterative(bst.rootNode);
	}


	@Test
	public void testJova(){
		bst.insert(20);
		bst.insert(10);
		bst.insert(40);
		bst.insert(15);
		bst.insert(2);
		bst.insert(7);

		bst.printIterativePreOrder(bst.rootNode);
	}


	@Test
	public void testConvertList(){
		bst.insert(510);
		bst.insert(90);
		bst.insert(100);
		bst.insert(225);
		bst.insert(300);
		bst.insert(265);
		bst.insert(400);
		bst.insert(700);
		bst.insert(695);
		bst.insert(800);
		bst.insert(54);
		bst.insert(40);
		bst.insert(650);
		bst.insert(697);
		bst.insert(750);
		bst.insert(850);
		CollectionsHelper.printCollection(bst.getNodesInorder());


		JTreeNode<Integer> n = bst.convertToLinkList();

		while(n!=null){

			System.out.print(n.getValue()+",");
			n = n.getRightNode();
		}
	}

}

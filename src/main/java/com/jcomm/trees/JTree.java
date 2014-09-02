package com.jcomm.trees;


/**
 * This implementation of a binary tree is backed by an array.
 * 
 * @author Jovaughn
 *
 * @param <T>
 */
public abstract class JTree<T extends Comparable<T>> implements Tree<T> {

	private int count;
	private final T[] treeNodes;
	
	@SuppressWarnings("unchecked")
	public JTree(int treeMaxNodes){
		treeNodes = (T[]) new Object[treeMaxNodes];
	}
	
	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void insertValue(T val) {
		treeNodes[count++] = val;	
	}

	@Override
	public void removeValue(T val) {
		
		
	}

	
}

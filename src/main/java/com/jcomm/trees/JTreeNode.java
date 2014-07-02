package com.jcomm.trees;

/**
 * Standard node used can be used any type of binary tree.
 * 
 * @author Jovaughn
 *
 * @param <T>
 */
public class JTreeNode<T extends Comparable<T>> implements TreeNode<T> {

	private TreeNode<T> leftNode = null;
	private TreeNode<T> rightNode = null;
	private T val;
	
	public JTreeNode(T val){
		this.val = val;
	}
	
	
	/**
	 * A node is considered a leaf if it does not have any edges to any other vertices 
	 * (children).
	 * 
	 * return if this node is a leaf
	 */
	@Override
	public boolean isLeaf() {
		if(getLeftNode()==null && getRightNode()==null)
			return true;
		
		return false;
	}

	@Override
	public T getValue() {
		return val;
	}

	@Override
	public TreeNode<T> getLeftNode() {
		return leftNode;
	}
	
	public void setLeftNode(TreeNode<T> leftNode){
		this.leftNode = leftNode;
	}

	@Override
	public TreeNode<T> getRightNode() {
		return rightNode;
	}
	
	public void setRightNode(TreeNode<T> rightNode){
		this.rightNode = rightNode;
	}

}

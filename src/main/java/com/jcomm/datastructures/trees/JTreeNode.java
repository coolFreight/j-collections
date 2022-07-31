package com.jcomm.datastructures.trees;

/**
 * Standard node used can be used any type of binary tree.
 * 
 * @author Jovaughn
 *
 * @param <T>
 */
public class JTreeNode<T extends Comparable<T>> implements TreeNode<T> {

	private JTreeNode<T> leftNode = null;
	private JTreeNode<T> rightNode = null;
	private JTreeNode<T> parentNode = null;
	private T val;

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	private boolean isVisited = false;
	
	public JTreeNode(T val){
		this.val = val;
	}
	
	
	public void setValue(T val){
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
	public JTreeNode<T> getLeftNode() {
		return leftNode;
	}
	
	public void setLeftNode(JTreeNode<T> leftNode){
		this.leftNode = leftNode;
	}

	@Override
	public JTreeNode<T> getRightNode() {
		return rightNode;
	}
	
	public void setRightNode(JTreeNode<T> rightNode){
		this.rightNode = rightNode;
	}
	
	public boolean isEqual(T value){
	
		return val.compareTo(value) == 0;
		
	}
	
	@Override 
	public Object clone(){
		
		 JTreeNode<T> obj = new  JTreeNode<T>(this.val);
		 obj.setLeftNode(this.leftNode);
		 obj.setRightNode(this.rightNode);
		return leftNode;
		
	}


	@Override
	public String toString() {
		return "JTreeNode [val=" + val + "]";
	}


	public JTreeNode<T> getParentNode() {
		return parentNode;
	}


	public void setParentNode(JTreeNode<T> parentNode) {
		this.parentNode = parentNode;
	}


	public boolean isBST(JTreeNode<T> n){

		boolean leftVal;
		boolean rightVal;
		if(n==null)
			return true;

		if(n.getLeftNode()!=null && n.getLeftNode().getValue().compareTo(n.getValue())>0)
			return false;
		else
			leftVal = isBST(n.getLeftNode());

		if(n.getRightNode()!=null && n.getRightNode().getValue().compareTo(n.getValue()) < 0)
			return false;
		else
			rightVal = isBST(n.getRightNode());


		return (leftVal&&rightVal);
	}


}

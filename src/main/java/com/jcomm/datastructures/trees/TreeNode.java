package com.jcomm.datastructures.trees;



public interface TreeNode<T extends Comparable<?>> extends Node<T>{
	
	TreeNode<T> getLeftNode();
	TreeNode<T> getRightNode();
	
}

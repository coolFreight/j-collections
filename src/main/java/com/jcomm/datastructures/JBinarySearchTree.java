package com.jcomm.datastructures;

import com.jcomm.trees.JTreeNode;

/***
 * Binary Tree property
 * 
 * Insertion into a Binary Search Tree every child node of a parent will either
 * be the left child or right child depending on on some property of the BST.
 * 
 * 
 * 
 * @author Jovaughn Lockridge
 * 
 */
public class JBinarySearchTree<T extends Comparable<T>> {

	JTreeNode<T> rootNode = null;

	public JTreeNode<T> getRootNode() {
		return this.rootNode;
	}

	public void insert(T value) {

		JTreeNode<T> node = new JTreeNode<T>(value);

		if (rootNode == null)
			rootNode = node;
		else {

			JTreeNode<T> currentNode = getCurrentNode(rootNode, value);
			node.setParentNode(currentNode);
			
			if (currentNode.getValue().compareTo(node.getValue()) > 0)
				currentNode.setLeftNode(node);
			else
				currentNode.setRightNode(node);
			
			

		}

	}

	public void remove(T value) {

		JTreeNode<T> val = findValue(this.rootNode, value);
		if (val != null)
			remove(val);
	}
	
	private void removeFromTree(JTreeNode<T> source, JTreeNode<T> target){
		
		if(source.getParentNode()==null)
			this.rootNode = target;
		else{
			JTreeNode<T> parent = source.getParentNode();
			if(source.getValue().compareTo(parent.getValue())<=0)
				parent.setLeftNode(target);
			else
				parent.setRightNode(target);
			
		}
	}

	public void remove(JTreeNode<T> root) {

		if (root.getLeftNode() != null && root.getRightNode() != null) {
			// remove root node
			JTreeNode<T> newRoot = root.getRightNode();
			JTreeNode<T> newRootLeftLeaf = root.getLeftNode();
			JTreeNode<T> findLeftLeaf = newRoot.getLeftNode();

			if (findLeftLeaf == null) {
				newRoot.setLeftNode(newRootLeftLeaf);
			} else {
				while (findLeftLeaf.getLeftNode() != null) {
					findLeftLeaf = findLeftLeaf.getLeftNode();
				}
				findLeftLeaf.setLeftNode(newRootLeftLeaf);
			}
			removeFromTree(root, newRoot);
		} else if (root.getLeftNode() != null && root.getRightNode() == null) {
			JTreeNode<T> newRoot = root.getLeftNode();
			removeFromTree(root, newRoot);
		} else if (root.getLeftNode() == null && root.getRightNode() != null) {
			JTreeNode<T> newRoot = root.getRightNode();
			removeFromTree(root, newRoot);
		} else if (root.getLeftNode() == null && root.getRightNode() == null) {
			removeFromTree(root, null);
		}

	}

	private JTreeNode<T> getCurrentNode(JTreeNode<T> root, T insertValue) {

		if (root.getLeftNode() == null && root.getRightNode() == null)
			return root;

		if (root.getValue().compareTo(insertValue) > 0
				&& root.getLeftNode() != null)
			return getCurrentNode(root.getLeftNode(), insertValue);

		if ((root.getValue().compareTo(insertValue) < 0)
				&& (root.getRightNode() != null))
			return getCurrentNode(root.getRightNode(), insertValue);

		return root;

	}
	
	
	public JList<T> getNodesPreOrder() {

		JList<T> l = new JLinkedList<T>();
		getNodesPreOrder(this.rootNode, l);
		return l;
	}

	/**
	 * 1) Visit the root node
	 * 
	 * 2) Traverse the left subtree
	 * 
	 * 3) traverse the right subtree
	 * 
	 * 
	 * 
	 * @param root
	 */
	public void getNodesPreOrder(JTreeNode<T> root, JList<T> t) {

		if (root == null)
			return;

		t.add(root.getValue());
		getNodesPreOrder(root.getLeftNode(), t);
		getNodesPreOrder(root.getRightNode(), t);
	}

	/**
	 * 1) Traverse the left subtree 2) Visit the root node 3) traverse the right
	 * subtree
	 * 
	 * @param root
	 * @return list of nodes in inorder position
	 */
	public JList<T> getNodesInorder() {

		JList<T> l = new JLinkedList<T>();
		getNodesInorder(this.rootNode, l);
		return l;
	}

	private void getNodesInorder(JTreeNode<T> root, JList<T> list) {

		if (root == null)
			return;

		getNodesInorder(root.getLeftNode(), list);
		list.add(root.getValue());
		getNodesInorder(root.getRightNode(), list);
	}

	/**
	 * Searches the tree
	 * 
	 * @param value
	 * @return true if the value is found; false otherwise
	 */
	public boolean findValue(T value) {

		JTreeNode<T> val = findValue(this.rootNode, value);

		if (val == null)
			return false;

		return true;
	}

	private JTreeNode<T> findValue(JTreeNode<T> root, T value) {

		if (root == null)
			return null;

		if (value.equals(root.getValue()))
			return root;

		if (value.compareTo(root.getValue()) < 0)
			return findValue(root.getLeftNode(), value);

		return findValue(root.getRightNode(), value);
	}

	public void printPostorderTreeTraversal(JTreeNode<T> root) {

		if (root == null)
			return;

		printPostorderTreeTraversal(root.getLeftNode());
		printPostorderTreeTraversal(root.getRightNode());
		System.out.print(root.getValue() + ", ");
	}

	public void swapNodes(T firstValue, T secondValue) {

		JTreeNode<T> firstValueParentNode = findParentNode(this.rootNode,
				new JTreeNode<T>(null), firstValue);
		JTreeNode<T> secondValueParentNode = findParentNode(this.rootNode,
				new JTreeNode<T>(null), secondValue);

		if (((firstValueParentNode.getLeftNode() != null && secondValueParentNode
				.getLeftNode() != null)
				&& firstValueParentNode.getLeftNode().isEqual(firstValue) && secondValueParentNode
				.getLeftNode().isEqual(secondValue))) {

			JTreeNode<T> tempNode = firstValueParentNode.getLeftNode();
			firstValueParentNode.setLeftNode(secondValueParentNode
					.getLeftNode());
			secondValueParentNode.setLeftNode(tempNode);
		} else if (((firstValueParentNode.getRightNode() != null && secondValueParentNode
				.getRightNode() != null)
				&& firstValueParentNode.getRightNode().isEqual(firstValue) && secondValueParentNode
				.getRightNode().isEqual(secondValue))) {

			JTreeNode<T> tempNode = firstValueParentNode.getRightNode();
			firstValueParentNode.setRightNode(secondValueParentNode
					.getRightNode());
			secondValueParentNode.setRightNode(tempNode);
		} else if (((firstValueParentNode.getLeftNode() != null && secondValueParentNode
				.getRightNode() != null)
				&& firstValueParentNode.getLeftNode().isEqual(firstValue) && secondValueParentNode
				.getRightNode().isEqual(secondValue))) {

			JTreeNode<T> tempNode = firstValueParentNode.getLeftNode();
			firstValueParentNode.setLeftNode(secondValueParentNode
					.getRightNode());
			secondValueParentNode.setRightNode(tempNode);
		} else {
			JTreeNode<T> tempNode = firstValueParentNode.getRightNode();
			firstValueParentNode.setRightNode(secondValueParentNode
					.getLeftNode());
			secondValueParentNode.setLeftNode(tempNode);
		}
	}

	public <T extends Comparable<T>> JTreeNode<T> findParentNode(
			JTreeNode<T> node, JTreeNode<T> parentNode, T value) {

		JTreeNode<T> tempNode = null;
		if (node == null)
			return null;

		if (node.isEqual(value)) {

			tempNode = parentNode;

		} else {

			if (tempNode == null)
				tempNode = findParentNode(node.getLeftNode(), node, value);
			if (tempNode == null)
				tempNode = findParentNode(node.getRightNode(), node, value);
		}

		return tempNode;

	}

	// public void printLevelTreeTraversal(JTreeNode<T> root, JTreeNode<T>
	// parentNode) {
	//
	// System.out.print(root.getValue() + ", ");
	// System.out.print(root.getLeftNode().getValue() + ", ");
	// System.out.print(root.getRightNode().getValue() + ", ");
	// printLevelTreeTraversal(root.getLeftNode(), root.getRightNode());
	// }

	// private void printLevelTreeTraversal(JTreeNode<T> root,
	// JTreeNode<T> nextNode) {
	//
	// if (root == null)
	// return;
	//
	// if (root.getLeftNode() != null)
	// System.out.print(root.getLeftNode().getValue() + ", ");
	// if (root.getRightNode() != null)
	// System.out.print(root.getRightNode().getValue() + ", ");
	//
	// if (root.getLeftNode() != null)
	// printLevelTreeTraversal(nextNode, root.getLeftNode());
	// else if (root.getRightNode() != null)
	// printLevelTreeTraversal(nextNode, root.getRightNode());
	// else
	// printLevelTreeTraversal(nextNode, nextNode.getRightNode());
	//
	// }


}

package com.jcomm.trees;

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

			if (currentNode.getValue().compareTo(node.getValue()) > 0)
				currentNode.setLeftNode(node);
			else
				currentNode.setRightNode(node);

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
	public void printPreFixTreeTraversal(JTreeNode<T> root) {

		if (root == null)
			return;

		System.out.print(root.getValue() + ", ");
		printPreFixTreeTraversal(root.getLeftNode());
		printPreFixTreeTraversal(root.getRightNode());
	}

	/**
	 * 1) Traverse the left subtree
	 * 
	 * 2) Visit the root node
	 * 
	 * 3) traverse the right subtree
	 * 
	 * 
	 * 
	 * @param root
	 */
	public void printInorderTreeTraversal(JTreeNode<T> root) {

		if (root == null)
			return;

		printInorderTreeTraversal(root.getLeftNode());
		System.out.print(root.getValue() + ", ");
		printInorderTreeTraversal(root.getRightNode());
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
				.getLeftNode().isEqual(secondValue))){
			
			JTreeNode<T> tempNode = firstValueParentNode.getLeftNode();
			firstValueParentNode.setLeftNode(secondValueParentNode.getLeftNode());
			secondValueParentNode.setLeftNode(tempNode);
		}
		else if (((firstValueParentNode.getRightNode() != null && secondValueParentNode
				.getRightNode() != null)
				&& firstValueParentNode.getRightNode().isEqual(firstValue) && secondValueParentNode
				.getRightNode().isEqual(secondValue))) {

			JTreeNode<T> tempNode = firstValueParentNode.getRightNode();
			firstValueParentNode.setRightNode(secondValueParentNode.getRightNode());
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

	public static void main(String args[]) {

		JBinarySearchTree<Character> bst = new JBinarySearchTree<>();

		bst.insert('F');
		bst.insert('B');
		bst.insert('G');
		bst.insert('A');
		bst.insert('D');
		bst.insert('I');
		bst.insert('C');
		bst.insert('E');
		bst.insert('H');

		bst.printPreFixTreeTraversal(bst.getRootNode());
		System.out.println("");
		bst.printInorderTreeTraversal(bst.getRootNode());
		System.out.println("");
		bst.printPostorderTreeTraversal(bst.getRootNode());

		System.out.println("");
		System.out.println(bst.findParentNode(bst.getRootNode(), null, 'H'));

		bst.swapNodes('A', 'D');
		System.out.println("");
		bst.printInorderTreeTraversal(bst.getRootNode());
	}

}

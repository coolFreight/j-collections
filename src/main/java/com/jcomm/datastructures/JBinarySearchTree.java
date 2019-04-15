package com.jcomm.datastructures;

import com.jcomm.trees.JTreeNode;
import com.jcomm.trees.Tree;

import java.util.*;

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
public class JBinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    JTreeNode<T> rootNode = null;
    private int treeSize = 0;


    public JTreeNode<T> getRootNode() {
        return this.rootNode;
    }

    public void insert(T value) {
        JTreeNode<T> node = new JTreeNode<>(value);
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
        treeSize++;
    }

    public void remove(T value) {
        JTreeNode<T> val = findValue(this.rootNode, value);
        if (val != null)
            remove(val);
    }

    public void removeOldWay(T value) {

        JTreeNode<T> val = findValue(this.rootNode, value);
        if (val != null)
            removeOldWay(val);
    }

    private void removeFromTree(JTreeNode<T> source, JTreeNode<T> target) {
        if (source.getParentNode() == null)
            this.rootNode = target;
        else {
            JTreeNode<T> parent = source.getParentNode();
            if (source.getValue().compareTo(parent.getValue()) <= 0)
                parent.setLeftNode(target);
            else
                parent.setRightNode(target);

        }
    }

    public void remove(JTreeNode<T> doomedNode) {
        JTreeNode<T> doomedParentNode = doomedNode.getParentNode();
        if (doomedNode.getLeftNode() != null && doomedNode.getRightNode() != null) {
            JTreeNode<T> leftMostNode = doomedNode.getRightNode();
            while (leftMostNode.getLeftNode() != null) {
                leftMostNode = leftMostNode.getLeftNode();
            }
            if (doomedNode.getValue().compareTo(rootNode.getValue()) == 0) {
                rootNode.setValue(leftMostNode.getValue());
                leftMostNode.getParentNode().setLeftNode(null);
            } else if (doomedParentNode.getLeftNode().getValue().compareTo(doomedNode.getValue()) == 0) {
                doomedParentNode.setLeftNode(leftMostNode);
                leftMostNode.getParentNode().setLeftNode(null);
            } else {
                doomedParentNode.setRightNode(leftMostNode);
                leftMostNode.getParentNode().setLeftNode(null);
            }
        } else if (doomedNode.getLeftNode() == null && doomedNode.getLeftNode() == null) {
            if (doomedParentNode.getLeftNode().getValue().compareTo(doomedNode.getValue()) == 0) {
                doomedParentNode.setLeftNode(null);
            } else {
                doomedParentNode.setRightNode(null);
            }
        } else {
            JTreeNode<T> replaceNode;
            if (doomedNode.getLeftNode() == null) {
                replaceNode = doomedNode.getRightNode();
            } else {
                replaceNode = doomedNode.getLeftNode();
            }

            if (doomedParentNode.getLeftNode().getValue().compareTo(doomedNode.getValue()) == 0) {
                doomedParentNode.setLeftNode(replaceNode);
            } else {
                doomedParentNode.setRightNode(replaceNode);
            }
        }
    }

    public void removeOldWay(JTreeNode<T> root) {
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
     * <p>
     * 2) Traverse the left subtree
     * <p>
     * 3) traverse the right subtree
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

    /**
     * Gets lowest value in the tree;
     *
     * @return lowest value
     */
    public T findMinimum() {
        return findMinimum(this.rootNode);
    }

    private T findMinimum(JTreeNode<T> root) {

        if (root.getLeftNode() == null)
            return root.getValue();

        return findMinimum(root.getLeftNode());

    }

    public void printLevelTreeTraversal(JTreeNode<T> root, JTreeNode<T> parentNode) {

        System.out.print(root.getValue() + ", ");
        System.out.print(root.getLeftNode().getValue() + ", ");
        System.out.print(root.getRightNode().getValue() + ", ");
        printLevelTreeTraversal(root.getLeftNode(), root.getRightNode());
    }


    /**
     * Gets a list of all values from the tree in level order from right to left
     * <p>
     * The following tree</br></br>
     * <b>root value 9</b></br>
     * <b>left child value 3</b></br>
     * <b>right child value 12</b></br>
     * will give a list <b>9, 3, 12</b>
     *
     * @return a list of all values in the tree
     */
    public JLinkedList<T> getLevelOrder() {
        return getLevelOrder(this.rootNode);
    }


    private JLinkedList<T> getLevelOrder(JTreeNode<T> root) {
        JQueue<JTreeNode<T>> q = new JLinkedList<>();
        JLinkedList<T> l = new JLinkedList<>();
        q.enqueue(root);
        System.out.println(root.getValue());
        while (!q.isEmpty()) {
            root = q.dequeue();
            l.add(root.getValue());
            if (root.getLeftNode() != null) {
                q.enqueue(root.getLeftNode());
//                System.out.print(root.getLeftNode() + " ");
            }
            if (root.getRightNode() != null) {
                q.enqueue(root.getRightNode());
//                System.out.print(root.getRightNode() +" ");
            }

        }
        return l;
    }


    public void printIterative(JTreeNode n) {
        final Deque<JTreeNode<T>> nodeTracker = new ArrayDeque<>();
        nodeTracker.push(n);
        while (!nodeTracker.isEmpty()) {

            while (n.getLeftNode() != null && !n.getLeftNode().isVisited()) {
                nodeTracker.push(n.getLeftNode());
                n = n.getLeftNode();

            }

            System.out.println(n.getValue());
            n.setVisited(true);
            nodeTracker.pop();

            if (n.getRightNode() != null && !n.getRightNode().isVisited()) {
                nodeTracker.push(n.getRightNode());
            }
            n = nodeTracker.peek();
        }
    }

    public void printIterativePreOrder(JTreeNode n) {
        final Deque<JTreeNode<T>> nodeTracker = new ArrayDeque<>();
        if (n == null)
            return;
        System.out.println("n : " + n.getValue());

        nodeTracker.push(n);

        while (n != null) {

            if (n.getLeftNode() != null && !n.getLeftNode().isVisited()) {
                n = n.getLeftNode();
                n.setVisited(true);
                nodeTracker.push(n);
                System.out.println("n : " + n.getValue());
            } else {

                n = nodeTracker.pop();
                if (n.getRightNode() != null && !n.getRightNode().isVisited()) {
                    n = n.getRightNode();
                    n.setVisited(true);
                    nodeTracker.push(n);
                    System.out.println("n : " + n.getValue());

                }
            }
        }

    }

    public JTreeNode<T> convertToLinkList() {

        JTreeNode<T> left = convertList(rootNode.getLeftNode(), true);

        while (left.getLeftNode() != null) {

            left = left.getLeftNode();
        }

        JTreeNode<T> farLeft = left;
        if (farLeft != null) {

            while (farLeft.getRightNode() != null) {
                farLeft = farLeft.getRightNode();
            }
            farLeft.setRightNode(rootNode);
        }

        JTreeNode<T> right = convertList(rootNode.getRightNode(), false);

        if (right != null) {
            while (right.getLeftNode() != null) {

                right = right.getLeftNode();
            }
            rootNode.setRightNode(right);
        }

        return (left != null ? left : rootNode);
    }


    private JTreeNode<T> convertList(JTreeNode<T> current, boolean isLeft) {

        if (current == null) {
            return null;
        }

        JTreeNode<T> left = convertList(current.getLeftNode(), true);
        JTreeNode<T> right = convertList(current.getRightNode(), false);

        if (left != null && right == null) {
            left.setRightNode(current);
            current.setLeftNode(left);
            return current;
        }

        if (left == null && right != null) {
            current.setRightNode(right);
            right.setLeftNode(current);
            return current;
        }

        if (left != null && right != null) {
            left.setRightNode(current);
            current.setLeftNode(left);

            current.setRightNode(right);
            right.setLeftNode(current);
            if (isLeft) {
                return right;
            }

            return left;
        }


        return current;

    }

    public <T extends Integer> Set<List<Integer>> getPathsSumToVal(JTreeNode start, int sumVal) {
        int[] parents = new int[this.treeSize];

        int[] vals = new int[this.treeSize];
        Set<List<Integer>> paths = new HashSet<>();

        getPathsSumToVal(start, 0, -1, parents, vals);

        int numOfTimes = this.treeSize - 1;
        while (numOfTimes > 0) {
            for (int x = numOfTimes; x >= 0; x--) {
                LinkedList<Integer> path = new LinkedList<>();
                int sum = 0;
                int traverser = x;
                while (traverser >= 0) {
                    sum += vals[traverser];
                    path.addFirst(vals[traverser]);
                    traverser = parents[traverser];
                }

                if (sum == sumVal)
                    paths.add(path);
            }

            numOfTimes--;
        }
        return paths;


    }


    private int getPathsSumToVal(JTreeNode<Integer> current, int index, int parentIndex, int[] parents, int[] vals) {
        if (current == null)
            return index - 1;

        parents[index] = parentIndex;
        vals[index] = current.getValue();

        parentIndex = index;

        index = getPathsSumToVal(current.getLeftNode(), index + 1, parentIndex, parents, vals);
        return getPathsSumToVal(current.getRightNode(), index + 1, parentIndex, parents, vals);
    }


    public int getTreeDiameter(JTreeNode<T> node) {

        int leftSubTreeDepth = getTreeDepth(node.getLeftNode(), 1);
        int rightSubTreeDepth = getTreeDepth(node.getRightNode(), 1);

        return leftSubTreeDepth + rightSubTreeDepth;
    }

    private int getTreeDepth(JTreeNode<T> node, int index) {

        if (node == null)
            return index - 1;

        int leftCount = getTreeDepth(node.getLeftNode(), index + 1);
        int rightCount = getTreeDepth(node.getRightNode(), index + 1);

        if (leftCount > rightCount)
            return leftCount;

        return rightCount;

    }


    public static void main(String[] args) {
        JBinarySearchTree tree = new JBinarySearchTree();
        tree.insert("jov");
        tree.insert("zoo");
        tree.insert("aaa");

//		tree.printIterativePreOrder(tree.rootNode);

        CollectionsHelper.printCollection(tree.getLevelOrder(tree.rootNode));
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void insertValue(T val) {

    }

    @Override
    public void removeValue(T val) {

    }

    @Override
    public T minimum() {
        return null;
    }

    @Override
    public T maximum() {
        return null;
    }
}

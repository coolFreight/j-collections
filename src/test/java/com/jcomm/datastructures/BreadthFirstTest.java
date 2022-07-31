package com.jcomm.datastructures;

import com.jcomm.datastructures.trees.JTreeNode;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadthFirstTest {


    @Test
    public void printLevelTree() {

        JBinarySearchTree<Integer> bst = new JBinarySearchTree<>();
        bst.insert(20);
        bst.insert(37);
        bst.insert(10);
        bst.insert(3);
        bst.insert(15);
        bst.insert(40);
        bst.insert(25);

        Deque<JTreeNode<Integer>> q = new LinkedList<>();
        JTreeNode<Integer> rootNode = bst.getRootNode();
        q.add(rootNode);
        String str = "";
        while(!q.isEmpty()){
            JTreeNode<Integer> node = q.removeFirst();
            System.out.print(node.getValue()+", ");
            str += node.getValue()+", ";
            if(!node.isLeaf()){
                if(node.getLeftNode() != null) {
                    q.add(node.getLeftNode());
                }
                if(node.getRightNode() != null) {
                    q.add(node.getRightNode());
                }
            }
        }
        assertEquals("20, 10, 37, 3, 15, 25, 40, ", str);
    }
}

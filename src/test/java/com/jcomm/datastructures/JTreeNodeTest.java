package com.jcomm.datastructures;

import com.jcomm.trees.JTreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jova on 5/17/15.
 */
public class JTreeNodeTest {


    @Test
    public void testIsBST(){

        JTreeNode<Integer> root = new JTreeNode<>(5);
        root.setLeftNode(new JTreeNode<>(3));
        root.setRightNode(new JTreeNode<>(7));

        Assert.assertTrue(root.isBST(root));

         root = new JTreeNode<>(5);
        root.setLeftNode(new JTreeNode<>(3));
        JTreeNode<Integer> left7 = new JTreeNode<>(7);
        left7.setRightNode(new JTreeNode<>(3));
        root.setRightNode(left7);

        Assert.assertTrue(root.isBST(root));
    }
}

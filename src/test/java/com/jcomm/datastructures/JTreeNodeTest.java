package com.jcomm.datastructures;

import com.jcomm.datastructures.trees.JTreeNode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by jova on 5/17/15.
 */
public class JTreeNodeTest {


    @Test
    @Disabled
    public void testIsBST() {

        JTreeNode<Integer> root = new JTreeNode<>(5);
        root.setLeftNode(new JTreeNode<>(3));
        root.setRightNode(new JTreeNode<>(7));

        assertTrue(root.isBST(root));

        root = new JTreeNode<>(5);
        root.setLeftNode(new JTreeNode<>(3));
        JTreeNode<Integer> left7 = new JTreeNode<>(7);
        left7.setRightNode(new JTreeNode<>(3));
        root.setRightNode(left7);

        assertTrue(root.isBST(root));
    }
}

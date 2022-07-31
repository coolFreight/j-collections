package com.jcomm.datastructures;

import com.jcomm.datastructures.trees.JTreeNode;
import org.junit.jupiter.api.Test;

public class DepthFirstTest {



    @Test
    public void travereTreePreOrder() {

        JBinarySearchTree<Integer> bst = new JBinarySearchTree<>();
        bst.insert(20);
        bst.insert(37);
        bst.insert(10);
        bst.insert(3);
        bst.insert(15);
        bst.insert(40);
        bst.insert(25);
//        String result = preOrder(bst.rootNode, "");
//        Assert.assertEquals("20, 10, 3, 15, 37, 25, 40, ", result);

//        Math.pow()
    }

    private String preOrder(JTreeNode<Integer> node, String result){
        if(node == null) {
            return result;
        }
        result += node.getValue()+", ";
        System.out.println(node.getValue());
        result = preOrder(node.getLeftNode(), result);
        return preOrder(node.getRightNode(), result);
    }
}

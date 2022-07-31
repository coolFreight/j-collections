package com.jcomm.datastructures;


import com.jcomm.exceptions.IllegalOperationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JLinkedListTest {

    @Test
    public void testGetIndex() {

        JLinkedList<Integer> l = new JLinkedList<>();
        l.add(5);
        l.add(3);
        l.add(10);

        assertEquals(Integer.valueOf(5), l.getValueAtIndex(0));
        l.removeFirst();
        assertEquals(Integer.valueOf(3), l.getValueAtIndex(0));
        assertEquals(Integer.valueOf(10), l.getValueAtIndex(1));
    }


    @Test
    public void testGetIndexOutOfBounds() {

        JLinkedList<Integer> l = new JLinkedList<>();
        l.add(5);
        l.add(3);
        l.add(10);

        Assertions.assertThatThrownBy(() -> l.getValueAtIndex(4)).isInstanceOf(IllegalOperationException.class);
    }


    @Test
    public void testReverseList() {

        JLinkedList<Integer> l = new JLinkedList<>();
        l.add(5);
        l.add(3);
        l.add(10);

        CollectionsHelper.printCollection(l);
        l.reverseList();

        CollectionsHelper.printCollection(l);

        assertEquals(10, l.removeFirst().intValue());
        assertEquals(3, l.removeFirst().intValue());
        assertEquals(5, l.removeFirst().intValue());
    }

    @Test
    public void testFindCyleInLinkedList() {

        Link<Integer> l1 = new Link<>(34);
        Link<Integer> l2 = new Link<>(90);
        Link<Integer> l3 = new Link<>(109);
        Link<Integer> l4 = new Link<>(4);
        Link<Integer> l5 = new Link<>(76);
        Link<Integer> l6 = new Link<>(3424);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        Link<Integer> slow = l1;
        Link<Integer> fast = l1.next;
        while (slow.next != null && fast.next != null) {

            System.out.println("slow=" + slow.getElement());
            System.out.println("fast=" + fast.getElement());

            if (slow == fast) {
                System.out.println("Cycle detected");
                break;
            }

            slow = slow.next;
            fast = fast.next;
            if (fast.next != null)
                fast = fast.next;

        }

        JLinkedList<Integer> list = new JLinkedList<>();
        list.testProtectedMethod();

    }

    private class Link<P> {

        public P element;
        public Link<P> next = null;

        public Link(P element) {
            this.element = element;
        }

        public void setElement(P element) {
            this.element = element;

        }

        public P getElement() {
            return element;
        }

        public void setNext(Link<P> next) {
            this.next = next;

        }

        public Link<P> getNext() {
            return next;

        }
    }

}

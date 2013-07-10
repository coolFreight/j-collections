/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.datastructures;

import java.util.Iterator;

/**
 *
 * @author jova
 */
public class LinkedList<T> implements Iterable<T> {

    private Link<T> head;
    private Link<T> last;

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T item) {

        Link newLink = new Link();
        newLink.setElement(item);

        if (isEmpty()) {
            head = newLink;
        } else {
            last.setNext(newLink);
        }
        last = newLink;
    }

    public T getFirst() {
        if (isEmpty()) {
            //
        }
        return head.getElement();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class Link<T> {

        public T element;
        public Link next = null;

        public void setElement(T element) {
            this.element = element;

        }

        public T getElement() {
            return element;
        }

        public void setNext(Link next) {
            this.next = next;

        }

        public Link getNext() {
            return next;

        }
    }

    private class LinkedListIterator implements Iterator<T> {

        Link<T> link = head;

        @Override
        public boolean hasNext() {
            return link != null;
        }

        @Override
        public T next() {
            Link<T> temp = link;
            link = link.next;
            return temp.getElement();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static void main(String args[]) {

        LinkedList<Integer> l = new LinkedList<>();

        l.add(15);
        l.add(5);

        for (Integer i : l) {
            System.out.println(i);
        }
    }
}

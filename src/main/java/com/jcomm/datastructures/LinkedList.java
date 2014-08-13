/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.datastructures;

import java.util.Iterator;

import com.jcomm.exceptions.IllegalOperationException;

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

        Link<T> newLink = new Link<>();
        newLink.setElement(item);

        if (isEmpty()) {
            head = newLink;
        } else {
            last.setNext(newLink);
        }
        last = newLink;
    }

    public T remove() {
        if (head == null) {
            throw new IllegalOperationException();
        }
        T element = head.getElement();
        head = head.getNext();

        return element;
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

    private class Link<P> {

        public P element;
        public Link<P> next = null;

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


}

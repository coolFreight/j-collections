/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.datastructures;

import java.util.Iterator;

import com.jcomm.exceptions.IllegalOperationException;

/**
 *
 * @author Jovaughn Lockridge
 */
public class JLinkedList<T extends Comparable<T>> implements JQueue<T>, JList<T> {

    private Link<T> head;
    private Link<T> last;
    private int indexCount = 0;

    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Get value at the specified index.  The indexes are based on 0 -> length-1.
     * 
     * @param index
     * @return value at index
     * 
     * @throws IllegalOperationException if the index does not exist
     */
    public T getValueAtIndex(int index){
    	
    	if(index >= this.indexCount)
    		throw new IllegalOperationException("Index does not exist");
    	
    	int count = 0;
    	for(T val : this){
    		if(count==index)
    			return val;
    		count++;
    	}
    	
    	return null;
    	
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
        this.indexCount++;
    }

    
    /**
     *  Removes the first element in the list.
     * 
     * @return first element in the list
     */
    public T removeFirst() {
        if (head == null) {
            throw new IllegalOperationException();
        }
        T element = head.getElement();
        head = head.getNext();
        
        this.indexCount--;
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

	@Override
	public void enqueue(T val) {
		this.add(val);
	}

	@Override
	public T dequeue() {
		return removeFirst();
	}


	@Override
	public T peek() {
		return head.getElement();
	}

	@Override
	public boolean contains(T val) {
		
		for(T t : this)
			if(t.compareTo(val)==0)
				return true;
		
		return false;
	}


}

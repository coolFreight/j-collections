/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.datastructures;

import com.jcomm.exceptions.IllegalOperationException;

import java.util.Iterator;

/**
 *
 * @author Jovaughn Lockridge
 */
public class JLinkedList<T> implements JQueue<T>, JList<T> {

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


    public T removeLast() {
        if (head == null) {
            throw new IllegalOperationException();
        }

        Link<T> temp = head;
        if(head.getNext() == null){
            head = null;
            last = null;
            return temp.getElement();
        }

        while(temp.getNext().getNext()!=null){

            temp = temp.getNext();
        }

        last = temp;
        Link<T> val = temp.getNext();
        temp.setNext(null);
        return val.getElement();

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

    public class Link<P> {

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

    @Override
    public boolean contains(T item){

        for(T e : this){
            if(e.equals(item))
                return true;
        }

        return false;
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



	
	/**
	 * The number of of items in the queue
	 */
	@Override
	public int size() {	
		return  this.indexCount;
	}
	
	/**
	 *  Add input list to the end of this list. 
	 * 
	 * @throws IllegalArgumentException if input list is null
	 */
	@Override
	public void addAll(JList<T> list) {
		
		if(list == null)
			throw new IllegalArgumentException("Input is null");
		
		for(T node : list)
			this.add(node);
		
	}


    public void reverseList(){
        last = head;
        Link<T> curr = head;
        Link<T> prev = null;
        while(curr != null){
            Link<T> temp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = temp;
        }
        head = prev;
    }

    protected void testProtectedMethod(){
        System.out.println("Called protected method ");
    }

    public static void main(String[] args) {

    }

    private int getCountOfNodes(Link<T>  head){
        if(head == null) {
            return 0;
        }

        int count = getCountOfNodes(head.next);
        return ++count;
    }


    public int getCountOfNodes(){
        return getCountOfNodes(head);
    }

    public void removeNode(int indexToRemove) {
        int index = 0;
        int nodeBefore = indexToRemove-1;
        Link<T> node = head;

        while(index != nodeBefore) {
            node = node.next;
            index++;
        }

        if(node.next == null) {
            return;
        } else if(node.next.next != null){
            node.next = node.next.next;
        }

        System.out.println(head);
    }

}

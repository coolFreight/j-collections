package com.jcomm.datastructures;

public interface JQueue<T> {
	
	
	/**
	 * Inserts val into FIFO queue. 
	 */
	void enqueue(T val);
	
	/**
	 * Removes first item in front of the FIFO queue.
	 * 
	 * @return first item
	 */
	T dequeue();
	
	/**
	 * Will get the first item in the queue
	 * 
	 * @retun value
	 */
	T peek();
	
	boolean isEmpty();
	
	int size();
	
}

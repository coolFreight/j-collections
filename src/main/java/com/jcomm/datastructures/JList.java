package com.jcomm.datastructures;

public interface JList<T> extends Iterable<T> {
	
	void add(T item);
	boolean contains(T val);

}

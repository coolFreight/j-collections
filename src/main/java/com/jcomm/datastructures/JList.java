package com.jcomm.datastructures;

public interface JList<T> extends Iterable<T> {
	
	T removeFirst();
	T getFirst();
	void add(T item);
	void addAll(JList<T> list);
}

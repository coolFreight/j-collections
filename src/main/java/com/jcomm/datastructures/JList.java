package com.jcomm.datastructures;

public interface JList<T> extends Iterable<T> {

	T removeLast();
	T removeFirst();
	boolean contains(T item);
	T getFirst();
	void add(T item);
	void addAll(JList<T> list);
}

package com.jcomm.datastructures;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

	private T arr[];
	private int numOfElements;
	private int front = 0;
	private int back = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private final int size;

	public BlockingQueue(int size) {
		this.size = size;
		arr = (T[]) new Object[size];
	}

	public void addElement(T element) {
		try {
			lock.lock();
			if (isFull()) {
				condition.await();
			}
			arr[back] = element;
			numOfElements++;
			back++;
		} catch (InterruptedException e) {

			e.printStackTrace();
		} finally {

			lock.unlock();
		}
	}

	public T removeElement() {

		T element = null;
		try {
			lock.lock();
			if (isEmpty()) {
				condition.await();
			}
			element = (T) arr[front];
			front++;
			numOfElements--;
			return element;

		} catch (InterruptedException e) {
			return null;

		} finally {
			lock.unlock();
		}

	}

	public boolean isFull() {
		try {
			lock.lock();
			return numOfElements == size;
		} finally {
			lock.unlock();
		}
	}

	public boolean isEmpty() {

		try {
			lock.lock();
			return front == back;
		} finally {
			lock.unlock();
		}

	}

	public int size() {
		try {
			lock.lock();
			return size;
		} finally {
			lock.unlock();
		}

	}
}

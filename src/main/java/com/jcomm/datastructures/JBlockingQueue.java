package com.jcomm.datastructures;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class JBlockingQueue<T> {

	@GuardedBy("lock") private T arr[];
	private int numOfElements;
	private int front = 0;
	private int back = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private final int SIZE;

	public JBlockingQueue(int size) {
		this.SIZE = size;
		arr = (T[]) new Object[size];
	}

	public void addElement(T element) {
		try {
			lock.lock();
			if (isFull()) {
				condition.await();
			}
			arr[back%SIZE] = element;
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
			if (isEmpty()) 
				condition.await();

			element = (T) arr[front];
			front++;
			numOfElements--;
			condition.signal();
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
			return numOfElements == SIZE;
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
			return SIZE;
		} finally {
			lock.unlock();
		}

	}
}

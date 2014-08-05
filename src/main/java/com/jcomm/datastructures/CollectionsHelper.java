package com.jcomm.datastructures;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CollectionsHelper {

	public static void printArray(int a[]) {

		for (int x : a) {
			System.out.print(x+ ",");
		}
		System.out.println("");
	}

	public static <T> void printArray(T array[]) {

		for (T i : array) {
			System.out.print(i + ",");
		}

		System.out.println("");
	}

	public static Integer[] randomIntArray(int size) {

		Integer a[] = new Integer[size];
		HashSet<Integer> set = new HashSet<>();
		for (int x = 0; x < a.length; x++) {

			Random r = new Random();
			Integer i = r.nextInt(size * 10);

			while (set.contains(i)) {
				i = r.nextInt(size);
			}
			set.add(i);
			a[x] = i;
		}
		return a;
	}

	// pseudocode array index starts at 1
	public static void printPseudoHiLoMid(int arrayLength) {

		if (arrayLength == 1)
			return;

		printPseudoHiLoMid(1, arrayLength);

	}

	private static void printPseudoHiLoMid(int lo, int hi) {

		int midi = (lo + hi) / 2;

		System.out.println("Hi :" + hi);
		System.out.println("mid :" + midi);
		System.out.println("lo :" + lo);
		System.out.println("******************************************");

		if (lo == hi) {
			return;
		}

		printPseudoHiLoMid(lo, midi);
		printPseudoHiLoMid(midi + 1, hi);
		// print left
	}

	/**
	 * Will swap the values between the given input index params
	 * 
	 * @param array
	 * @param index
	 * @param otherIndex
	 * 
	 */
	public static <T> void swapValues(T array[], int index, int otherIndex) {
		T tempVal = array[otherIndex];
		array[otherIndex] = array[index];
		array[index] = tempVal;
	}
	
	public static <T extends Comparable<T>> T[] heapSort(T array[]) {
		int heapSize = 0; // heapSize is important to know in determining when
							// to the recursive calls
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null)
				break;
			heapSize++;
		}
		buildMaxHeap(array, heapSize);

		for (int i = heapSize; i > 1; i--) {
			CollectionsHelper.swapValues(array, 0, heapSize - 1);
			heapSize--;
			maxHeapify(array, 0, heapSize);
		}

		return array;
	}

	private static <T extends Comparable<T>> void buildMaxHeap(T A[],
			int heapSize) {

		int nonLeaves = (A.length / 2) - 1;
		for (int i = nonLeaves; i >= 0; i--) {
			maxHeapify(A, i, heapSize);
		}
	}

	private static <T extends Comparable<T>> void maxHeapify(T array[],
			int index, int heapSize) {

		int leftIndex = getLeftChildIndex(index);
		int rightIndex = getRightChildIndex(index);
		int largestIndex = index;

		if (leftIndex < heapSize
				&& array[index].compareTo(array[leftIndex]) == -1)
			largestIndex = leftIndex;

		if (rightIndex < heapSize
				&& array[largestIndex].compareTo(array[rightIndex]) == -1)
			largestIndex = rightIndex;

		if (largestIndex != index) {
			CollectionsHelper.swapValues(array, largestIndex, index);
			maxHeapify(array, largestIndex, heapSize);
		}

	}

	private static <T> int getLeftChildIndex(int index) {
		return (2 * index) + 1;
	}

	private static <T> int getRightChildIndex(int index) {
		return (2 * index) + 2;
	}
	
	
	public static void quickSort(int A[]){
		
		quickSort(A, 0, A.length-1);
	}

	private static void quickSort(int A[], int start, int end){
		
		if(start==end)
			return;
			
			int q = partition(A, start, end);
			quickSort(A, start, q);
			quickSort(A, q+1, end);
	}

	private static int partition(int A[], int start, int end) {

		int i = start - 1;
		int x = A[end];

		for (int j = start; j < end; j++) {
			if (A[j] < x) {
				int temp = A[j];
				A[j] = A[i + 1];
				A[i + 1] = temp;
				i++;
			}	
		}
		int temp = A[end];
		A[end] = A[i+1];
		A[i+1] = temp;
		return i;

	}
	public static void rotateArray(Integer []arr, int index, int rotateBy){

		for(int x = 0; x<arr.length; x++){	
			int newLocation = ((x + rotateBy)-1) % arr.length;
			swapValues(arr, x, newLocation);
		}
	}
	
	public static <T> void printCollection (Collection<T> collection, String separtor){
		
		for(T item : collection){
			System.out.print(item+separtor);
		}
		System.out.println();
	}
	
	

}

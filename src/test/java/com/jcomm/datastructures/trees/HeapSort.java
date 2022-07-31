package com.jcomm.datastructures.trees;

import com.jcomm.datastructures.CollectionsHelper;

import java.util.Scanner;
import java.util.StringTokenizer;

public class HeapSort {


	
	/**
	 * This the array must have continuous data in order 
	 * for it to sorted properly
	 * 
	 * 
	 * @param array
	 * @param index
	 */
	public static <T extends Comparable<T>> T[] heapSort(T array[], int index) {
		int heapSize = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null)
				break;
			heapSize++;
		}
		
		
		
		return array;
	}

	private static <T extends Comparable<T>> void maxHeapify(T array[], int index, int heapSize){
		
		int leftIndex = getLeftChildIndex(index);
		int rightIndex = getRightChildIndex(index);
		int largestIndex = index;
		
		if(leftIndex <= heapSize && array[index].compareTo(array[leftIndex]) == -1)
			largestIndex = leftIndex;
		
		if(rightIndex <= heapSize && array[index].compareTo(array[rightIndex]) == -1)
			largestIndex = rightIndex;
		
		if(largestIndex != index){
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCases = Integer.parseInt(sc.nextLine());
		while(testCases>0){
			int arraySize = Integer.parseInt(sc.nextLine());
			long []array = new long[arraySize];
			int arrayCount = 0;

			StringTokenizer st = new StringTokenizer(sc.nextLine());
			while(arrayCount <= arraySize-1){

				long  l = Long.parseLong(st.nextToken());
				array[arrayCount] = l;
				arrayCount= 1 + arrayCount;
			}

			//getCountArrayShifts(array);
			testCases--;
		}
	}


}

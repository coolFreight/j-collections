package com.jcomm.datastructures;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CollectionsHelper {

	public static void main(String[] args) {

		int a[] = {1,1,1,1,2};
		System.out.println(CollectionsHelper.one2oneHundred(a));
	}

	static int[] mostFrequentSum(int n) {

		if (n <= 0)
			return new int[0];

		int[] countFrequencyArray = new int[100000];
		TreeMap<Integer, Integer> mostFrequentMap = new TreeMap<>();
		
		for (int count = 0; count < countFrequencyArray.length; count++) {

			int rolledVal = 0;
			
			int numberOfRolls = n;
			while(numberOfRolls > 0){
				rolledVal += rollDice();
				
				numberOfRolls--;
			}	
			
			int tempVal =0;
			if(mostFrequentMap.containsKey(rolledVal))
				tempVal = mostFrequentMap.get(rolledVal);
			
			mostFrequentMap.put(rolledVal, ++tempVal);
		}

		Map<Integer, Integer> mostFrequentNums = new TreeMap<>();
		int mostFrequent = 0;
		for(Map.Entry<Integer,Integer> entry :  mostFrequentMap.entrySet()) {
			  int rolledDice = entry.getKey();
			  int rolledCount = entry.getValue();

			System.out.println("rolled " + rolledDice + " " + rolledCount
					+ " times.");

			if (rolledCount > 0 && rolledCount > mostFrequent) {
				mostFrequent = rolledCount;
				mostFrequentNums = new HashMap<>();
				mostFrequentNums.put(rolledDice, rolledCount);
			} else if (rolledCount > 0 && rolledCount == mostFrequent) {
				mostFrequentNums.put(rolledDice, rolledCount);
			}
		}

		int arr[] = new int[mostFrequentNums.keySet().size()];
		Object freq[] = mostFrequentNums.keySet().toArray();

		for (int count = 0; count < arr.length; count++) {

			arr[count] = (Integer) freq[count];
		}

		return arr;

	}
	
	public static void jo(){
	int val = randomVal();
    val += randomVal();
    val += randomVal();
    val += randomVal();
    val += randomVal();
    
    System.out.println(val);
	}
//	
	static int one2oneHundred(int[] dice) {
	    
	    int []domain = new int[100];
	    int dice1 = dice[0];
	    int dice2 = dice[1];
	    int dice3 = dice[2];
	    int dice4 = dice[3];
	    int dice5 = dice[4];
	    
	    for(int x =0; x<domain.length; x++){
	      
	        domain[x] = x+1;
	    }
	    
	    int internalDice[] = new int[6];
	    for(int x =0; x<internalDice.length; x++){
		      
	        domain[x] = x+1;
	    }
	    
	    
	    
	    int val = (dice2+ dice3 +dice4+dice5)%4;
	    
	    return domain[val];

	    
	    
	    
	}

	
	private static int randomVal(){
		
		
		Random r = new Random();
	
		
		int val = r.nextInt(21);
		 
		
		System.out.println(val);
		return val;		
	}

	static int rollDice() {

		Random r = new Random();
		int val = r.nextInt(7);
		while (val == 0) {
			val = r.nextInt(7);
		}
		
		
		return val;

	}

	public static void printArray(int a[]) {

		for (int x : a) {
			System.out.println(x);
		}
	}

	public static <T> void printArray(T array[]) {

		for (T i : array) {
			System.out.print(i);
		}

		System.out.println("");
	}

	public static int[] randomIntArray(int size) {

		int a[] = new int[size];
		HashSet<Integer> set = new HashSet<>();
		for (int x = 0; x < a.length; x++) {

			Random r = new Random();
			Integer i = r.nextInt(size * 10);

			while (set.contains(i)) {
				i = r.nextInt(size);
			}

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
	
	public static <T> void HeapSort(T array[], int index){
		
		int heapSize =0;
		for(int i= 0;  i<array.length; i++ ){
			
			if(array[i]== null)
				break;		
			heapSize++;
		}
	}
	
	/**
	 * Will swap the values between the given input index params
	 * 
	 * @param array
	 * @param index
	 * @param otherIndex
	 * @return the array with the values swapped
	 */
	public static <T> void swapValues(T array[], int index, int otherIndex){	
		T tempVal = array[otherIndex];
		array[otherIndex] = array[index];
		array[index] = tempVal;		
	}

}

package com.jcomm.datastructures;


import java.util.HashSet;
import java.util.Random;

public class CollectionsHelper {
	
	
	public static void main(String [] args){
		
		CollectionsHelper.printPseudoHiLoMid(16);		
	}
	
	
	public static <T> void  printArray(T array[]){
		
		for(T i : array){	
			System.out.print(i);
		}
		
		System.out.println("");
	}
	
	
	public static int[] randomIntArray(int size){
		
		int a[] = new int[size];
		HashSet<Integer> set = new HashSet<>();
		for(int x =0; x<a.length; x++){
			
			Random r = new Random();
			Integer i = r.nextInt(size*10);
			
			while(set.contains(i)){
				i = r.nextInt(size*10);
			}
			
			a[x] = i;
		}
		return a;
	}
	
	//pseudocode array index starts at 1
		public static void printPseudoHiLoMid(int arrayLength){
			
			if(arrayLength == 1)
				return;
			
			
			printPseudoHiLoMid(1, arrayLength);
			
			
		}
		
		private static void printPseudoHiLoMid(int lo, int hi){
			
			int midi = (lo+hi)/2;

			System.out.println("Hi :"+hi);
			System.out.println("mid :"+midi);
			System.out.println("lo :"+lo);
			System.out.println("******************************************");
			
			if(lo ==hi){
				return;
			}

			printPseudoHiLoMid(lo, midi);
			printPseudoHiLoMid(midi+1, hi);
			//print left
		}


}

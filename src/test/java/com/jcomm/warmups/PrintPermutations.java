package com.jcomm.warmups;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.jcomm.datastructures.CollectionsHelper;


public class PrintPermutations {
	
	
	
	public static void main (String args[]){
		
		char [] word = {'c','a', 't', 's'};
		
		PrintPermutations p = new PrintPermutations();
		p.lottoNumbers();
	}
	
	
	public void swapper(int A[], int x) {

		if(x == 1 ){
			CollectionsHelper.printArray(A);
			return;
		}
		
		for(int k = x; k > 0; k--){
			swapper(A, x-1);
			rotate(A, x);
		}
		
		return;
	}
	
	public void rotate(int A[], int x){
		int k = A.length-x;
		int c = A[k];
		
		for(int j = k; j < A.length-1; j++){
			
			A[j] = A[j+1];
		}	
		
		A[A.length-1] = c;
	}
	
	
	public void lottoNumbers(){
		
		int number [] = new int [5];
		
		Map<Integer, Integer> map = new HashMap<>();
		
		
		Random r = new Random();
		
		int count = 5;
		while(count > 0){
			
			int num = r.nextInt(60);
			if(!map.containsKey(num)){
				map.put(num, num);
				number[count-1] = num;
				count--;
			}
		
		}
		
		CollectionsHelper.printArray(number);
		System.out.println("powerball number"+ new Random().nextInt(36));
	}
}

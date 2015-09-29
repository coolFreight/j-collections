package com.jcomm.warmups;

import java.util.LinkedList;
import java.util.List;

import com.jcomm.datastructures.CollectionsHelper;

public class KnapSack {

	public static void main(String args[]) {

		// int V[] = {9,7,8};
		// int W[] = {5,3,2};

		// System.out.println(KnapSack.maxVal(V, W, 2, 5, 0));

		int A[] = { 1, 2, 3, 1, 1, 5 };
		// 0 1 2 3 4 5
		CollectionsHelper.printCollection((minHops(A, 0, 0)));

	}

	// {1,2}; size = 2
	// 0 1 2 3 4 5
	static int minhops(int A[], int index, int hops) {

		if (index >= A.length - 1)
			return hops;

		int val = 9999999;
		for (int x = A[index]; x > 0; x--) {

			int temp = minhops(A, index + x, hops + 1);
			if (temp < val)
				val = temp;
		}

		return val;
	}

	static int maxVal(int V[], int W[], int index, int weightAvailable, int val) {

		if (index < 0)
			return val;

		int leftVal = maxVal(V, W, index - 1, weightAvailable, val);
		int rightVal = 0;
		if (weightAvailable - W[index] < 0)
			rightVal = 0;
		else {
			rightVal = maxVal(V, W, index - 1, (weightAvailable - W[index]),
					V[index] + val);
		}
		if (rightVal > leftVal)
			return rightVal;
		return leftVal;
	}
	
	public static LinkedList<Integer> minHops(int A[], int idx, int hops){
		
		LinkedList<Integer> result = new LinkedList<>();
		if(idx == A.length-1){
			
			result.addFirst(idx);
			return result;
		}
		
		int val = 99999;
		LinkedList<Integer> list = new LinkedList<>();
		for(int x = A[idx]; x>0; x--){
			
			LinkedList<Integer> templist = minHops(A, idx+x, hops+1);
			if(templist.size()< val){
				
				val = templist.size();
				list = templist;
				list.addFirst(idx);
			}
		}
		
		result.addAll(list);
		return result;
	}
}

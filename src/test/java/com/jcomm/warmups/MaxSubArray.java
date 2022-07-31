package com.jcomm.warmups;

public class MaxSubArray{

	public static void main(String args[]){
	
	MaxSubArray ms = new MaxSubArray();
	
	//int A[] = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
	
	//all negative values returns the day to purchase to lose the least amount of money
	//int A[] = {-63,-25,-93,-16,-23,-7,-5,-22};
	
	//my book not example
	int A[] = {13,24,-28,33};
	
	Tuple tuple = ms.maxSubArray(A, 0, A.length-1);
	
	System.out.print("the max profit is : "+tuple.getSum()+" lo: "+tuple.getLo()+" hi: "+tuple.getHi());
	}



	public Tuple maxSubArray(int A[], int lo, int hi){

		if(lo==hi){
			System.out.println("hi :" +hi);
			return new Tuple(A[lo], lo, hi);
		}
		
		int mid = (lo+hi)/2;
		
		Tuple leftTuple;
		Tuple rightTuple;
		Tuple maxCrossTuple;
		
		
		//System.out.println("hi :" +mid);
		//left traversal
		leftTuple = maxSubArray(A, lo, mid);
		
		//right traversal
		rightTuple = maxSubArray(A, mid+1, hi);
		
		//max mid traversal
		maxCrossTuple = maxCrossSubArray(A, lo, mid, hi);
		
		if(leftTuple.getSum() > rightTuple.getSum() && leftTuple.getSum() > maxCrossTuple.getSum())
			return leftTuple;
		else if(rightTuple.getSum()> maxCrossTuple.getSum())
			return rightTuple;
		else	
			return maxCrossTuple;
	}

	private Tuple maxCrossSubArray(int A[], int lo, int mid, int hi){
		
		int leftSum = 0;
		int maxLeftSum = -99999;
		int maxLeftIndex = 0;
		for ( int leftDown = mid; leftDown >= lo; leftDown--){
			
			leftSum = leftSum + A[leftDown];
			
			if(leftSum > maxLeftSum){
				maxLeftSum = leftSum;
				maxLeftIndex = leftDown;
			
			}	
		}
		int rightSum = 0;
		int maxRightSum = -99999;
		int maxRightIndex = 0;
		for (int  rightUp = mid+1; rightUp <= hi; rightUp++){
			
			rightSum = rightSum + A[rightUp];
			
			if(rightSum > maxRightSum){
				maxRightSum = rightSum;
				maxRightIndex = rightUp;
			
			}	
		}
		
		return new Tuple((maxLeftSum+maxRightSum), maxLeftIndex, maxRightIndex);
	}
	
	public class Tuple{
		
		private final int sum;
		private final int lo;
		private final int hi;
		
		
		public Tuple(int sum, int lo, int hi){
			this.sum = sum;
			this.lo = lo;
			this.hi = hi;
		}
		
		public int getSum(){
			return sum;
		}
		
		public int getLo(){
			return lo;
		}
		
		public int getHi(){
			return hi;
		}
	}

}